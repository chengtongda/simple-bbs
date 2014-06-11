package com.github.chengtongda.simplebbs.exception;

public class MagicCodeException extends Exception {
	
	private static final long serialVersionUID = 7825851876992806310L;

	public MagicCodeException() {
		super();
	}

	public MagicCodeException(String reason){
		super(reason);
	}
}
