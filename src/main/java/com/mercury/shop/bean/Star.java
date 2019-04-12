//package com.mercury.shop.bean;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.SequenceGenerator;
//import javax.persistence.Table;
//
//@Entity
//@Table(name = "msi_star")
//public class Star {
//	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO, generator = "MSI_STAR_SEQ_GEN")
//	@SequenceGenerator(name = "MSI_STAR_SEQ_GEN", sequenceName = "MSI_STAR_SEQ", allocationSize = 1)
//	private int id;
//	@Column
//	private int product_id;
//	@Column
//	private float star;
//	public Star() {
//		super();
//	}
//	public Star(int id) {
//		super();
//		this.id = id;
//	}
//	public int getId() {
//		return id;
//	}
//	public void setId(int id) {
//		this.id = id;
//	}
//	public float getStar() {
//		return star;
//	}
//	public void setStar(float star) {
//		this.star = star;
//	}
//	@Override
//	public String toString() {
//		return "Star [id=" + id + ", star=" + star + "]";
//	}
//	
//}
