package com.mercury.shop.bean;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "msi_product")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "MSI_PRODUCT_SEQ_GEN")
	@SequenceGenerator(name = "MSI_PRODUCT_SEQ_GEN", sequenceName = "MSI_PRODUCT_SEQ", allocationSize = 1)
	private int id;
	@Column
	private String name;
	@Column
	private String brand;
	@Column
	private int price;
	@Column
	private int stock;
	@Column
	private String image;
	@Column
	private float discount;
	@Column
	private String description;
	@Column
	private double star;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "category_id")
	//@JsonIgnore
	private Category category;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "product")
	private Set<Comment> comments;
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "msi_product_msi_color", joinColumns = {
			@JoinColumn(name = "product_id", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "color_id", referencedColumnName = "id") })
	private Set<Color> colors;
	public Product() {
		super();
	}
	public Product(String name, String brand, int price, int stock, String image, float discount, String description,
			double star, Category category, Set<Comment> comments, Set<Color> colors) {
		super();
		this.name = name;
		this.brand = brand;
		this.price = price;
		this.stock = stock;
		this.image = image;
		this.discount = discount;
		this.description = description;
		this.star = star;
		this.category = category;
		this.comments = comments;
		this.colors = colors;
	}




	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", brand=" + brand + ", price=" + price + ", stock=" + stock
				+ ", image=" + image + ", discount=" + discount + ", description=" + description + ", star=" + star
				+ ", category=" + category + ", comments=" + comments + ", colors=" + colors + "]";
	}





	public Set<Comment> getComments() {
		return comments;
	}





	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}





	public float getDiscount() {
		return discount;
	}



	public void setDiscount(float discount) {
		this.discount = discount;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	public double getStar() {
		return star;
	}



	public void setStar(double star) {
		this.star = star;
	}



	public Category getCategory() {
		return category;
	}



	public void setCategory(Category category) {
		this.category = category;
	}




	public Set<Color> getColors() {
		return colors;
	}



	public void setColors(Set<Color> colors) {
		this.colors = colors;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}


}