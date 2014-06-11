package com.github.chengtongda.simplebbs.pipeline;

import java.io.IOException;
import java.util.Map;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.frame.FrameDecoder;

import com.github.chengtongda.simplebbs.constants.ProtocolConstants;
import com.github.chengtongda.simplebbs.domain.Protocol;
import com.github.chengtongda.simplebbs.exception.MagicCodeException;
import com.github.chengtongda.simplebbs.utils.BBSUtils;

/**
 * –≠“ÈΩ‚¬Î
 * @author tongda
 *
 */
public class ProtocolDecoder extends FrameDecoder {

	@SuppressWarnings("unchecked")
	@Override
	protected Object decode(ChannelHandlerContext ctx, Channel channel,
			ChannelBuffer buffer) throws Exception {
		
		Protocol protocol = (Protocol) ctx.getAttachment();
		if(protocol == null){
			protocol = new Protocol();
			if(buffer.readableBytes() < ProtocolConstants.MAGIC_CODE_SIZE + ProtocolConstants.DATA_LENTH_SIZE){
				return null;
			}else{
				byte magicCode = buffer.readByte();
				checkMagicCode(magicCode);
				protocol.setMagicCode(magicCode);
				protocol.setDataLength(buffer.readInt());
			}
		}
		if(buffer.readableBytes() < protocol.getDataLength()){
			ctx.setAttachment(protocol);
			return null;
		}
		
		byte[] datas = new byte[protocol.getDataLength()];
		buffer.readBytes(datas);
		protocol.setDatas(datas);
		ctx.setAttachment(null);
		return (Map<String, String>) BBSUtils.getObjectFromByteArray(datas);
	}

	private void checkMagicCode(byte magicCode) throws IOException, MagicCodeException{
		if(magicCode != ProtocolConstants.MAGIC_CODE){
			throw new MagicCodeException("magic code error,magicCode="+ magicCode);
		}
	}
	
}
