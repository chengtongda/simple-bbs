package com.github.chengtongda.simplebbs.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.github.chengtongda.simplebbs.domain.BBSMessage;


public class ServerMessageQueue {

	private static List<BBSMessage> messageQueue = Collections.synchronizedList(new ArrayList<BBSMessage>());
	
	public static void addMessage(BBSMessage bbsMessage){
		messageQueue.add(bbsMessage);
	}
	
	public static void removeMessage(BBSMessage bbsMessage){
		messageQueue.remove(bbsMessage);
	}
	
	public static List<BBSMessage> getMessageQueue(){
		return messageQueue;
	}
}
