package org.unibl.etf.ip.bean;

import java.io.Serializable;
import java.util.Date;

public class MessageBean implements Serializable {

	private int id;
	private String text;
	private Date date;
	private boolean read;
	private int user_id;
	
	public MessageBean() {
		// TODO Auto-generated constructor stub
	}

	public MessageBean(int id, String text, Date date, boolean read, int user_id) {
		super();
		this.id = id;
		this.text = text;
		this.date = date;
		this.read = read;
		this.user_id = user_id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public boolean isRead() {
		return read;
	}

	public void setRead(boolean read) {
		this.read = read;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	@Override
	public String toString() {
		return "MessageBean [id=" + id + ", text=" + text + ", date=" + date + ", read=" + read + ", user_id=" + user_id
				+ "]";
	}
	
	

}
