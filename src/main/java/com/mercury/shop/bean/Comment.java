package com.mercury.shop.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "msi_comment")
public class Comment {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "MSI_COMMENT_SEQ_GEN")
	@SequenceGenerator(name = "MSI_COMMENT_SEQ_GEN", sequenceName = "MSI_COMMENT_SEQ", allocationSize = 1)
	private int id;
	@Column(name="comments")
	private String comment;
	@Column
	private int star;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "product_id")
	@JsonIgnoreProperties("comments")
	private Product product;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public int getStar() {
		return star;
	}
	public void setStar(int star) {
		this.star = star;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Comment() {
		super();
	}
	public Comment(int id, String comment, int star, Product product) {
		super();
		this.id = id;
		this.comment = comment;
		this.star = star;
		this.product = product;
	}
	@Override
	public String toString() {
		return "Comment [id=" + id + ", comment=" + comment + ", star=" + star + ", product=" + product + "]";
	}
	
	
}
