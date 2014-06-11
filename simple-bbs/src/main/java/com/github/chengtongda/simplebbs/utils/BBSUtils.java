package com.github.chengtongda.simplebbs.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

import com.github.chengtongda.simplebbs.constants.BBSConstants;
import com.github.chengtongda.simplebbs.domain.BBSMessage;



public class BBSUtils {
	
	public static Object getObjectFromByteArray(byte[] data) throws IOException, ClassNotFoundException{
		return new ObjectInputStream(new ByteArrayInputStream(data)).readObject();
	}
	
	public static BBSMessage getMessageFromByteArray(Map<String,String> dataMap){
		if(dataMap == null){
			return null;
		}
		return new BBSMessage(dataMap.get(BBSConstants.NICK),dataMap.get(BBSConstants.CONTENT));
	}
	
	public static byte[] encode(BBSMessage message) throws IOException {
		Map<String,String> dataMap = convertMessageToMap(message);
		if(dataMap == null){
			return null;
		}
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(baos);
		oos.writeObject(dataMap);
		return baos.toByteArray();
	}
	
	public static byte[] encode(Map<String,String> msg) throws IOException {
		if(msg == null){
			return null;
		}
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(baos);
		oos.writeObject(msg);
		return baos.toByteArray();
	}
	
	public static byte[] encode(int data) throws IOException{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(baos);
		dos.writeInt(data);
		return baos.toByteArray();
	}
	
	public static Map<String,String> convertMessageToMap(BBSMessage message){
		Map<String,String> dataMap = new HashMap<String,String>();
		if(message==null){
			return null;
		}
		dataMap.put(BBSConstants.NICK, message.getNick());
		dataMap.put(BBSConstants.CONTENT, message.getContent());
		return dataMap;
	}
}
