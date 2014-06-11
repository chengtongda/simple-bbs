package com.github.chengtongda.simplebbs.pipeline;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;

import com.github.chengtongda.simplebbs.domain.BBSMessage;
import com.github.chengtongda.simplebbs.manager.BBSClientManager;
import com.github.chengtongda.simplebbs.worker.PrintWorker;

public class ClientBizProcesser extends  SimpleChannelUpstreamHandler{

	private Executor messageWorkers = Executors.newFixedThreadPool(20);

	private BBSClientManager bbsManager;
	
	public ClientBizProcesser(BBSClientManager bbSManage) {
		super();
		this.bbsManager = bbSManage;
	}

	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e)
			throws Exception {
		super.messageReceived(ctx, e);
		BBSMessage msg = (BBSMessage) e.getMessage();
		messageWorkers.execute(new PrintWorker(bbsManager, msg));
	}

	@Override
	public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e)
			throws Exception {
		super.channelConnected(ctx, e);
	}
	
	
	
}
