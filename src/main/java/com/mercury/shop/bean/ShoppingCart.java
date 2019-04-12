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
@Entity
@Table(name = "msi_shopping_cart")
public class ShoppingCart {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "MSI_SHOPPING_CART_SEQ_GEN")
	@SequenceGenerator(name = "MSI_SHOPPING_CART_SEQ_GEN", sequenceName = "MSI_SHOPPING_CART_SEQ", allocationSize = 1)
	private int id;
	@Column
	private int qty;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	private User user;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "product_id")
	private Product product;
	public ShoppingCart() {
		super();
	}
	public ShoppingCart(int qty, User user, Product product) {
		super();
		this.qty = qty;
		this.user = user;
		this.product = product;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	@Override
	public String toString() {
		return "ShoppingCart [id=" + id + ", qty=" + qty + ", user=" + user + ", product=" + product + "]";
	}
	@Override
	public boolean equals(Object obj) {
		ShoppingCart op =  (ShoppingCart)obj;
		return op.getProduct().getId() == product.getId() && op.getUser().getId() == user.getId();
	}
}
