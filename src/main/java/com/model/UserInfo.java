package com.model;

public class UserInfo {
	private int userid;
    private String username;
    private String password;
    private String fullname;
    private String email;
    private String phone;
    private String role;
    private int enabled;
     
    public UserInfo()  {
         
    }
 
    public UserInfo(String username, String password) {
        this.username = username;
        this.password = password;
    }
    public UserInfo(int userid ,String username, String password,String fullname, String email,String phone, String role,int enabled) {
    	this.userid = userid;
    	this.username = username;
        this.password = password;
        this.fullname = fullname;
    	this.email = email;
        this.phone = phone;
        this.role = role;
        this.enabled = enabled;
    }

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

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

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public int getEnabled() {
		return enabled;
	}

	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}




    
 
}