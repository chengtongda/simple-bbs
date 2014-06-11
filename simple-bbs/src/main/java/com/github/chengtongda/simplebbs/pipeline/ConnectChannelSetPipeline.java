package com.github.chengtongda.simplebbs.pipeline;

import java.util.Set;

import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.SimpleChannelHandler;

public class ConnectChannelSetPipeline extends SimpleChannelHandler {

	private Set<Channel> channels;

	public ConnectChannelSetPipeline(Set<Channel> channels) {
		super();
		this.channels = channels;
	}

	@Override
	public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e)
			throws Exception {
		super.channelConnected(ctx, e);
		channels.add(e.getChannel());
	}

	@Override
	public void channelClosed(ChannelHandlerContext ctx, ChannelStateEvent e)
			throws Exception {
		super.channelClosed(ctx, e);
		channels.remove(e.getChannel());
	}
	
}
