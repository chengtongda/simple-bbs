package com.github.chengtongda.simplebbs.worker;

import com.github.chengtongda.simplebbs.domain.BBSMessage;
import com.github.chengtongda.simplebbs.manager.BBSServerManager;


public class MessageWorker implements Runnable{

	private BBSServerManager bbsManager;
	
	private BBSMessage message;
	
	public MessageWorker(BBSServerManager bbsManager, BBSMessage message) {
		super();
		this.bbsManager = bbsManager;
		this.message = message;
	}

	@Override
	public void run() {
		bbsManager.sendMessageToAll(message);
	}
	
}
