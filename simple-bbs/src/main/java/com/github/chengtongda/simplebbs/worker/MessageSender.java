package com.github.chengtongda.simplebbs.worker;

import java.util.Iterator;
import java.util.Set;

import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelFutureListener;

import com.github.chengtongda.simplebbs.domain.BBSMessage;
import com.github.chengtongda.simplebbs.utils.ServerMessageQueue;


public class MessageSender implements Runnable{

	private Set<Channel> channels;

	public MessageSender(Set<Channel> channels) {
		super();
		this.channels = channels;
	}

	@Override
	public void run() {
		while(true){
			Iterator<BBSMessage> it = ServerMessageQueue.getMessageQueue().iterator();
			if(it.hasNext()){
				BBSMessage msg = it.next();
				writeMessage(msg);
				it.remove();
			}
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO
			}
		}
	}
	
	private void writeMessage(BBSMessage bbsMessage){
		for(Channel channel:channels){
			ChannelFuture future = channel.write(bbsMessage);
			future.addListener(new ChannelFutureListener() {
				
				@Override
				public void operationComplete(ChannelFuture future) throws Exception {
					if(!future.isSuccess() && future.isDone()){
						System.out.println("ni chu cuo le~~");
					}
				}
			});
			if(!future.isSuccess() && future.isDone()){
				System.out.println("ni chu cuo le~~");
			}
		}
	}
}
