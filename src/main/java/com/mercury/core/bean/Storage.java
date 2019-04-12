package com.mercury.core.bean;

import java.io.Serializable;

public class Storage implements Serializable{
	private static final long serialVersionUID = 1L;
	private int pid;
	private int pnum;
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public int getPnum() {
		return pnum;
	}
	public void setPnum(int pnum) {
		this.pnum = pnum;
	}
	@Override
	public String toString() {
		return "Storage [pid=" + pid + ", pnum=" + pnum + "]";
	}
	public Storage() {
		super();
	}
	public Storage(int pid, int pnum) {
		super();
		this.pid = pid;
		this.pnum = pnum;
	}
	
}
