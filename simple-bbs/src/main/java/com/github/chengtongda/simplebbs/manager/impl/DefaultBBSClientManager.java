package com.github.chengtongda.simplebbs.manager.impl;

import com.github.chengtongda.simplebbs.manager.BBSClientManager;


public class DefaultBBSClientManager implements BBSClientManager {

	@Override
	public void printOnScreen(String nick, String msg) {
		System.out.println();
		System.out.println(String.format("%s :> %s", nick,msg));
	}

}
