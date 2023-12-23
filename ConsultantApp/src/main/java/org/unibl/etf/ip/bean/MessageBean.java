package org.unibl.etf.ip.bean;

import java.io.Serializable;
import java.util.Date;

public class MessageBean implements Serializable {

	private int id;
	private String title;
	private String text;
	private Date date;
	private boolean read = false;
	private boolean responded = false;
	private int user_id;
	
	public MessageBean() {
		// TODO Auto-generated constructor stub
	}

	public MessageBean(int id, String title, String text, Date date, boolean read, boolean responded, int user_id) {
		super();
		this.id = id;
		this.title = title;
		this.text = text;
		this.date = date;
		this.read = read;
		this.responded = responded;
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public boolean isResponded() {
		return responded;
	}

	public void setResponded(boolean responded) {
		this.responded = responded;
	}

	@Override
	public String toString() {
		return "MessageBean [id=" + id + ", text=" + text + ", date=" + date + ", read=" + read + ", user_id=" + user_id
				+ "]";
	}
	
	

}
