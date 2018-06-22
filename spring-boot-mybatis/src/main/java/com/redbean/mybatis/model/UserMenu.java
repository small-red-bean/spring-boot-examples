package com.redbean.mybatis.model;

import java.io.Serializable;


public class UserMenu implements Serializable {
	private String username;

	private String menuname;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getMenuname() {
		return menuname;
	}

	public void setMenuname(String menuname) {
		this.menuname = menuname;
	}
}
