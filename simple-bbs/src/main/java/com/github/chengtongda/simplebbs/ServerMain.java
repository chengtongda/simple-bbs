package com.github.chengtongda.simplebbs;

import static java.util.concurrent.Executors.newCachedThreadPool;

import java.net.InetSocketAddress;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;

import com.github.chengtongda.simplebbs.manager.BBSServerManager;
import com.github.chengtongda.simplebbs.manager.impl.DefaultBBSServerManager;
import com.github.chengtongda.simplebbs.pipeline.ServerBizProcesser;
import com.github.chengtongda.simplebbs.pipeline.ConnectChannelSetPipeline;
import com.github.chengtongda.simplebbs.pipeline.MessageDecoder;
import com.github.chengtongda.simplebbs.pipeline.MessageEncoder;
import com.github.chengtongda.simplebbs.pipeline.ProtocolDecoder;
import com.github.chengtongda.simplebbs.pipeline.ProtocolEncoder;
import com.github.chengtongda.simplebbs.worker.MessageSender;

public class ServerMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		final BBSServerManager bbsManager = new DefaultBBSServerManager();
		final Set<Channel> channels = Collections.synchronizedSet(new HashSet<Channel>());
		
		ServerBootstrap bootstrap = new ServerBootstrap(
				new NioServerSocketChannelFactory(newCachedThreadPool(),newCachedThreadPool()));
		
		bootstrap.setPipelineFactory(new ChannelPipelineFactory(){
			@Override
			public ChannelPipeline getPipeline() throws Exception {
				ChannelPipeline pipeline = Channels.pipeline();
				//上行顺序:从头至尾
				//下行顺序:从尾至头
				pipeline.addLast("protocolDecode", new ProtocolDecoder());
				pipeline.addLast("messageDecode", new MessageDecoder());
				pipeline.addLast("bizProcesse", new ServerBizProcesser(bbsManager));
				pipeline.addLast("protocolEncode", new ProtocolEncoder());
				pipeline.addLast("messageEncode", new MessageEncoder());
				pipeline.addLast("connectSet", new ConnectChannelSetPipeline(channels));
				return pipeline;
			}
			
		});
		
		new Thread(new MessageSender(channels)).start();
		bootstrap.bind(new InetSocketAddress(1230));
	}

}
