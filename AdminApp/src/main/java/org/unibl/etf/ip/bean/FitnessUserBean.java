package org.unibl.etf.ip.bean;

import java.io.Serializable;
import java.util.Objects;

public class FitnessUserBean implements Serializable {

	private int id;
	private String name;
	private String lastName;
	private String city;
	private String username;
	private String password;
	private String avatar;
	private String mail;
	private boolean active = false;
	private boolean terminated;
	
	public FitnessUserBean() {
		// TODO Auto-generated constructor stub
	}
	
	

	public FitnessUserBean(int id) {
		super();
		this.id = id;
	}

	

	public FitnessUserBean(String name, String lastName, String city, String username, String password, String avatar,
			String mail, boolean active, boolean terminated) {
		super();
		this.name = name;
		this.lastName = lastName;
		this.city = city;
		this.username = username;
		this.password = password;
		this.avatar = avatar;
		this.mail = mail;
		this.active = active;
		this.terminated = terminated;
	}

	

	public boolean isTerminated() {
		return terminated;
	}



	public void setTerminated(boolean terminated) {
		this.terminated = terminated;
	}



	public FitnessUserBean(int id, String name, String lastName, String city, String username, String password,
			String avatar, String mail, boolean active, boolean terminated) {
		super();
		this.id = id;
		this.name = name;
		this.lastName = lastName;
		this.city = city;
		this.username = username;
		this.password = password;
		this.avatar = avatar;
		this.mail = mail;
		this.active = active;
		this.terminated = terminated;
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



	public String getCity() {
		return city;
	}



	public void setCity(String city) {
		this.city = city;
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



	public String getAvatar() {
		return avatar;
	}



	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}



	public String getMail() {
		return mail;
	}



	public void setMail(String mail) {
		this.mail = mail;
	}



	public boolean isActive() {
		return active;
	}



	public void setActive(boolean active) {
		this.active = active;
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
		FitnessUserBean other = (FitnessUserBean) obj;
		return id == other.id;
	}
}
