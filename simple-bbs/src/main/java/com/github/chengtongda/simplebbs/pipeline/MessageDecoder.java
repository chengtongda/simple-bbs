package com.github.chengtongda.simplebbs.pipeline;

import java.util.Map;

import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.oneone.OneToOneDecoder;

import com.github.chengtongda.simplebbs.utils.BBSUtils;

public class MessageDecoder extends OneToOneDecoder {

	@SuppressWarnings("unchecked")
	@Override
	protected Object decode(ChannelHandlerContext ctx, Channel channel,
			Object msg) throws Exception {
		if(msg != null && msg instanceof Map){
			return BBSUtils.getMessageFromByteArray((Map<String, String>) msg);
		}
		return null;
	}

}
