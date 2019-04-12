package com.mercury.shop.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mercury.shop.bean.Color;
import com.mercury.shop.bean.Product;
import com.mercury.shop.dao.CategoryDao;
import com.mercury.shop.dao.ProductDao;

@Service
public class FilterService {
	@Autowired
	ProductDao productdao;
	@Autowired
	CategoryDao categorydao;
	public List<Product> filterByCategory(int id) {
		List<Product> res = new ArrayList<Product>();
		List<Product> products = productdao.findAll();
		products.forEach((product) -> {
			if (product.getCategory().getId() == id) {
				System.out.println(product.getId());
				res.add(product);
			}
		});
		return res;
	}
	public List<Product> filterByColor(int id) {
		List<Product> res = new ArrayList<Product>();
		List<Product> products = productdao.findAll();
		products.forEach((product) -> {
			Set<Color> colors = product.getColors();
			colors.forEach((color) -> {
				if (color.getId() == id) {
					res.add(product);
				}
			});
		});
		return res;
	}
	public List<Product> filterByStock() {
		List<Product> res = new ArrayList<Product>();
		List<Product> products = productdao.findAll();
		products.forEach((product) -> {
			if(product.getStock() > 0) {
				res.add(product);
			}
		});
		return res;
	}
}
