package org.unibl.etf.ip.bean;

import java.io.Serializable;
import java.util.Objects;

public class AdminBean implements Serializable {

	private int id;
	private String name;
	private String lastName;
	private String username;
	private String password;
	private boolean loggedIn = false;
	
	public AdminBean() {
		// TODO Auto-generated constructor stub
	}

	
	
	public AdminBean(int id, String name, String lastName, String username, String password) {
		super();
		this.id = id;
		this.name = name;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
	}



	public AdminBean(String name, String lastName, String username, String password) {
		super();
		this.name = name;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
	}



	public AdminBean(int id) {
		super();
		this.id = id;
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getLastName() {
		return lastName;
	}



	public void setLastName(String lastName) {
		this.lastName = lastName;
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
	
	

	public boolean isLoggedIn() {
		return loggedIn;
	}



	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}



	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AdminBean other = (AdminBean) obj;
		return Objects.equals(id, other.id);
	}
}
