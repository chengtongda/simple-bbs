package com.github.chengtongda.simplebbs.pipeline;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;

import com.github.chengtongda.simplebbs.domain.BBSMessage;
import com.github.chengtongda.simplebbs.manager.BBSServerManager;
import com.github.chengtongda.simplebbs.worker.MessageWorker;


public class ServerBizProcesser extends  SimpleChannelUpstreamHandler{

	private Executor messageWorkers = Executors.newFixedThreadPool(20);

	private BBSServerManager bbsManager;
	
	public ServerBizProcesser(BBSServerManager bbsManager) {
		super();
		this.bbsManager = bbsManager;
	}

	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e)
			throws Exception {
		super.messageReceived(ctx, e);
		BBSMessage msg = (BBSMessage) e.getMessage();
		messageWorkers.execute(new MessageWorker(bbsManager, msg));
	}
	
}
