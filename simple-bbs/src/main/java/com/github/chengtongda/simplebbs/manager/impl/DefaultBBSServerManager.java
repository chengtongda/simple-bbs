package com.github.chengtongda.simplebbs.manager.impl;

import com.github.chengtongda.simplebbs.domain.BBSMessage;
import com.github.chengtongda.simplebbs.manager.BBSServerManager;
import com.github.chengtongda.simplebbs.utils.ServerMessageQueue;


public class DefaultBBSServerManager implements BBSServerManager {
	

	@Override
	public void sendMessageToAll(BBSMessage message) {
		ServerMessageQueue.addMessage(message);
		System.out.println("send:"+message);
	}
	
}
