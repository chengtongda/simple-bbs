package com.github.chengtongda.simplebbs.worker;

import com.github.chengtongda.simplebbs.domain.BBSMessage;
import com.github.chengtongda.simplebbs.manager.BBSClientManager;

public class PrintWorker implements Runnable{
	
	private BBSClientManager bbsManager;
	private BBSMessage msg;
	
	public PrintWorker(BBSClientManager bbsManager,BBSMessage msg){
		this.bbsManager = bbsManager;
		this.msg = msg;
	}
	
	@Override
	public void run() {
		this.bbsManager.printOnScreen(msg.getNick(), msg.getContent());
	}

}
