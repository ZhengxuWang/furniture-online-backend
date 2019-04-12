package com.mercury.shop.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "msi_color")
public class Color {
	@Id
	private int id;
	@Column
	private String type;
	
	public Color() {
		super();
	}
	public Color(int id) {
		super();
		this.id = id;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return "Color [id=" + id + ", type=" + type + "]";
	}
	
}
