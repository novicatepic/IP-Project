package org.unibl.etf.ip.bean;

import java.io.Serializable;

public class CategoryBean implements Serializable {

	private Integer id;
	private String name;
	//private int program_id;
	
	public CategoryBean() {
		// TODO Auto-generated constructor stub
	}
	
	

	public CategoryBean(Integer id, String naziv) {
		super();
		this.id = id;
		this.name = naziv;
		//this.program_id = program_id;
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

	/*public int getProgram_id() {
		return program_id;
	}

	public void setProgram_id(int program_id) {
		this.program_id = program_id;
	}*/

	
	
}
