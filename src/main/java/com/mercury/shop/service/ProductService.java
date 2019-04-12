package com.mercury.shop.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.mercury.shop.bean.Category;
import com.mercury.shop.bean.Color;
import com.mercury.shop.bean.Product;
import com.mercury.shop.dao.CategoryDao;
import com.mercury.shop.dao.ColorDao;
import com.mercury.shop.dao.CommentDao;
import com.mercury.shop.dao.ProductDao;
import com.mercury.shop.http.Response;

@Service
public class ProductService {
	

	@Autowired
	ProductDao productDao;
	@Autowired
	CategoryDao categoryDao;
	@Autowired
	ColorDao colorDao;
	@Autowired
	CommentDao commentDao;
	// add products list into cache
	public List<Product> getProducts() {
		return productDao.findAll();
	}
	public Product getProductByName(String name) {
		return productDao.findByName(name);
	}
	
	public List<Product> getProductsByPageAndSize(int page, int size) {
		return productDao.findAll(PageRequest.of(page, size)).getContent();
	}

	public Response saveProduct(Product product) {
		try {
			Category category = categoryDao.findById(product.getCategory().getId()).get();
			Set<Color> colors = product.getColors();
			Set<Color> colors_toinsert = new HashSet<>();
			colors.forEach((color)->{
				Color color_whole = colorDao.findById(color.getId()).get();
				colors_toinsert.add(color_whole);
			});
			product.setColors(colors_toinsert);
			product.setCategory(category);
			productDao.save(product);
			return new Response(true);
		} catch (Exception e) {
			return new Response(false);
		}
	}


	public Product getProduct(int id) {
		return productDao.findById(id).get();
	}

	public Response updateProduct(Product product) {
		try {
			Product p = productDao.getOne(product.getId());
			Category category = categoryDao.findById(product.getCategory().getId()).get();
			Set<Color> colors = product.getColors();
			Set<Color> colors_toinsert = new HashSet<>();
			colors.forEach((color)->{
				Color color_whole = colorDao.findById(color.getId()).get();
				colors_toinsert.add(color_whole);
			});
//			System.out.println("aaaaaaaaaaa--");
			p.setName(product.getName());
			p.setBrand(product.getBrand());
			p.setPrice(product.getPrice());
			p.setStock(product.getStock());
			p.setImage(product.getImage());
			p.setCategory(category);
			p.setColors(colors_toinsert);
			p.setComments(product.getComments());
			p.setDescription(product.getDescription());
			p.setDiscount(product.getDiscount());
			p.setStar(product.getStar());
			productDao.save(p);
			return new Response(true);
		} catch (Exception e) {
			return new Response(false);
		}
	}


	public Response deleteProduct(int id) {
		try {
			productDao.deleteById(id);
			return new Response(true);
		} catch (Exception e) {
			return new Response(false);
		}
	}

}
