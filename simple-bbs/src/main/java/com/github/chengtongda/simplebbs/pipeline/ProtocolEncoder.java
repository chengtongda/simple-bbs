package com.github.chengtongda.simplebbs.pipeline;

import java.util.Map;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.oneone.OneToOneEncoder;

import com.github.chengtongda.simplebbs.constants.ProtocolConstants;
import com.github.chengtongda.simplebbs.utils.BBSUtils;

public class ProtocolEncoder extends OneToOneEncoder {

	@SuppressWarnings("unchecked")
	@Override
	protected Object encode(ChannelHandlerContext ctx, Channel channel,
			Object msg) throws Exception {
		if(msg != null && msg instanceof Map){
			byte[] datas = BBSUtils.encode((Map<String,String>)msg);
			if(datas == null){
				return datas;
			}
			ChannelBuffer writeBuffer = ChannelBuffers.dynamicBuffer();
			
			byte[] dataLength = BBSUtils.encode(datas.length);
			//协议格式    magicode(1)+datalength(4)+datas
			writeBuffer.writeBytes(new byte[]{ProtocolConstants.MAGIC_CODE});
			writeBuffer.writeBytes(dataLength);
			writeBuffer.writeBytes(datas);
			
			return writeBuffer;
		}
		return null;
	}

}
