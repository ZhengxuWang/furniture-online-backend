package com.mercury.shop.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "msi_category")
public class Category {
	@Id
	private int id;
	@Column
	private String type;
	public Category() {
		super();
	}
	public Category(int id) {
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
		return "Category [id=" + id + ", type=" + type + "]";
	}
	
}
