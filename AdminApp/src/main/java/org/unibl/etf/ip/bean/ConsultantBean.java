package org.unibl.etf.ip.bean;

import java.io.Serializable;
import java.util.Objects;

public class ConsultantBean implements Serializable {

	private int id;
	private String username;
	private String password;
	private String name;
	private String lastName;
	
	public ConsultantBean() {
		// TODO Auto-generated constructor stub
	}

	public ConsultantBean(int id) {
		super();
		this.id = id;
	}

	public ConsultantBean(String username, String password, String name, String lastName) {
		super();
		this.username = username;
		this.password = password;
		this.name = name;
		this.lastName = lastName;
	}

	public ConsultantBean(int id, String username, String password, String name, String lastName) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.name = name;
		this.lastName = lastName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
		ConsultantBean other = (ConsultantBean) obj;
		return id == other.id;
	}
}
