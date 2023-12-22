package org.unibl.etf.ip.bean;

import java.io.Serializable;

public class AttributeBean implements Serializable {

	private Integer id;
	private String name;
	private String value;
	private int category_id;
	
	public AttributeBean() {
		// TODO Auto-generated constructor stub
	}

	
	
	public AttributeBean(String name, String value, int category_id) {
		super();
		this.name = name;
		this.value = value;
		this.category_id = category_id;
	}



	public AttributeBean(Integer id, String naziv, String vrijednost, int kategorija_id) {
		super();
		this.id = id;
		this.name = naziv;
		this.value = vrijednost;
		this.category_id = kategorija_id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String naziv) {
		this.name = naziv;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String vrijednost) {
		this.value = vrijednost;
	}

	public int getCategory_id() {
		return category_id;
	}

	public void setCategory_id(int kategorija_id) {
		this.category_id = kategorija_id;
	}

	
	
}
