package com.entity;

public class Login {
	private int pid;
	private int identity;
	private String uname;
	private String upwd;
	
	public Login() {
		
	}
	public Login(int id, int identity, String uname, String upwd) {
		this.pid = id;
		this.identity = identity;
		this.uname = uname;
		this.upwd = upwd;
	}
	public Login(String uname, String upwd) {
		this.uname = uname;
		this.upwd = upwd;
	}
	public int getIdentity() {
		return identity;
	}
	public void setIdentity(int identity) {
		this.identity = identity;
	}
	public int getId() {
		return pid;
	}
	public void setId(int id) {
		this.pid = id;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getUpwd() {
		return upwd;
	}
	public void setUpwd(String upwd) {
		this.upwd = upwd;
	}
}
