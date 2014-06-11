package com.github.chengtongda.simplebbs;

import java.net.InetSocketAddress;
import java.util.Scanner;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelFutureListener;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;

import com.github.chengtongda.simplebbs.constants.NetConstants;
import com.github.chengtongda.simplebbs.domain.BBSMessage;
import com.github.chengtongda.simplebbs.manager.BBSClientManager;
import com.github.chengtongda.simplebbs.manager.impl.DefaultBBSClientManager;
import com.github.chengtongda.simplebbs.pipeline.ClientBizProcesser;
import com.github.chengtongda.simplebbs.pipeline.MessageDecoder;
import com.github.chengtongda.simplebbs.pipeline.MessageEncoder;
import com.github.chengtongda.simplebbs.pipeline.ProtocolDecoder;
import com.github.chengtongda.simplebbs.pipeline.ProtocolEncoder;

public class ClientMain {
	
	private final ClientBootstrap clientBootStrap = 
			new ClientBootstrap(new NioClientSocketChannelFactory(Executors.newSingleThreadExecutor(), Executors.newCachedThreadPool()));
	private BBSClientManager bbsManager;
	private Channel channel;
	private String  ip = NetConstants.SERVER_IP;
	
	public ClientMain(BBSClientManager bbsManager){
		this.bbsManager = bbsManager;
		init();
	}
	
	public ClientMain(BBSClientManager bbsManager, String ip){
		this.bbsManager = bbsManager;
		this.ip = ip;
		init();
	}

	private void init() {
		clientBootStrap.setPipelineFactory(new ChannelPipelineFactory() {
			@Override
			public ChannelPipeline getPipeline() throws Exception {
				ChannelPipeline pipeline = Channels.pipeline();
				pipeline.addLast("protocol-decoder", new ProtocolDecoder());
				pipeline.addLast("message-decoder", new MessageDecoder());
				pipeline.addLast("bizPorcesser", new ClientBizProcesser(bbsManager));
				pipeline.addLast("protocol-encoder", new ProtocolEncoder());
				pipeline.addLast("message-encoder", new MessageEncoder());
				return pipeline;
			}
		});
		
		ChannelFuture future = this.clientBootStrap.connect(new InetSocketAddress(ip, NetConstants.PORT));
		this.channel = future.getChannel();
	}
	
	public Channel getChannel() {
		return channel;
	}

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		System.out.println("input the server address:");
		String ip = scan.next();
		System.out.print("input you name:");
		String nick = scan.next();
		
		BBSClientManager bbsManager = new DefaultBBSClientManager();
		ClientMain clientMain = new ClientMain(bbsManager, ip);
		Channel channel = clientMain.getChannel();
		while(true){
			System.out.print("please input:");
			String text = scan.next();
			ChannelFuture future = channel.write(new BBSMessage(nick,text));
			future.addListener(new ChannelFutureListener() {
				
				@Override
				public void operationComplete(ChannelFuture future) throws Exception {
					if(!future.isSuccess() && future.isDone()){
						System.out.println("error:"+future.getCause());
					}
				}
			});
		}
		
	}
	
}
