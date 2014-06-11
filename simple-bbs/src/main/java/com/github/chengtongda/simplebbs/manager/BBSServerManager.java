package com.github.chengtongda.simplebbs.manager;

import com.github.chengtongda.simplebbs.domain.BBSMessage;

public interface BBSServerManager {
	
	public void sendMessageToAll(BBSMessage message);
	
}
