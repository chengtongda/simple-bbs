package com.github.chengtongda.simplebbs.domain;

/**
 * 协议实体
 * @author tongda
 *
 */
public class Protocol {

	private byte magicCode;
	
	private int dataLength;
	
	private byte[] datas;

	public byte getMagicCode() {
		return magicCode;
	}

	public void setMagicCode(byte magicCode) {
		this.magicCode = magicCode;
	}

	public int getDataLength() {
		return dataLength;
	}

	public void setDataLength(int dataLength) {
		this.dataLength = dataLength;
	}

	public byte[] getDatas() {
		return datas;
	}

	public void setDatas(byte[] datas) {
		this.datas = datas;
	}
	
}
