package com.project.loginservice.pojo;

public class SellerLoginPojo
{

	private String username;
	private String password;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public SellerLoginPojo(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	@Override
	public String toString() {
		return "SellerLoginPojo [username=" + username + ", password=" + password + "]";
	}
	public SellerLoginPojo() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
