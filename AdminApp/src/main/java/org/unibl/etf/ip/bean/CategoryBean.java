package org.unibl.etf.ip.bean;

import java.io.Serializable;

public class CategoryBean implements Serializable {

	private Integer id;
	private String name;
	//private int program_id;
	private Boolean terminirana;
	
	public CategoryBean() {
		// TODO Auto-generated constructor stub
	}
	
	public CategoryBean(Integer id, String naziv, Boolean terminirana) {
		super();
		this.id = id;
		this.name = naziv;
		this.terminirana = terminirana;
	}


	public Boolean getTerminirana() {
		return terminirana;
	}

	public void setTerminirana(Boolean terminirana) {
		this.terminirana = terminirana;
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

	
}
