package com.github.chengtongda.simplebbs.domain;

/**
 * bbs��Ϣʵ��
 * @author chengtongda
 *
 */
public class BBSMessage {
	
	private final String nick;
	private final String content;
	
	public BBSMessage(String nick, String content) {
		super();
		this.nick = nick;
		this.content = content;
	}
	public String getNick() {
		return nick;
	}
	public String getContent() {
		return content;
	}
	
}
