package com.github.chengtongda.simplebbs.pipeline;

import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.oneone.OneToOneEncoder;

import com.github.chengtongda.simplebbs.domain.BBSMessage;
import com.github.chengtongda.simplebbs.utils.BBSUtils;

public class MessageEncoder extends OneToOneEncoder {

	@Override
	protected Object encode(ChannelHandlerContext ctx, Channel channel,
			Object msg) throws Exception {
		if(msg != null && msg instanceof BBSMessage){
			return BBSUtils.convertMessageToMap((BBSMessage)msg);
		}
		return null;
	}

}
