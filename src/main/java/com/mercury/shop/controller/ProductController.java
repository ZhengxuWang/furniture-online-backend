package com.mercury.shop.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mercury.shop.bean.Comment;
import com.mercury.shop.bean.Product;
import com.mercury.shop.dao.ProductDao;
import com.mercury.shop.http.Response;
import com.mercury.shop.service.FilterService;
import com.mercury.shop.service.ProductService;

@RestController
public class ProductController {

	@Autowired
	ProductDao productDao;

	@Autowired
	ProductService productService;
	@Autowired
	FilterService filterService;
	@GetMapping(value = "/products")
	public List<Product> getProducts(@RequestParam(value = "page", required = false) Optional<Integer> page, 
			@RequestParam(value = "size", required = false) Optional<Integer> size) {
		
		try {
			Thread.sleep(500);
		} catch(Exception e) {
			
		}
		if (page.isPresent() && size.isPresent()) {
			
//			System.out.println("AAAAAAAAAAAAAAA");
			return productService.getProductsByPageAndSize(page.get(), size.get());
		} else {

//			System.out.println("BBBBBBBBBBBBBBBB");
			List<Product> products = productService.getProducts();
			updateStars(products);

//			System.out.println("CCCCCCCCCCCCCCCC");
//			try {
//			Thread.sleep(10000);
//		} catch(Exception e) {
//			
//		}
//			System.out.println("DDDDDDDDDDDDDDDDD");
			return productService.getProducts();	
		}
	}
	
	@GetMapping("/list-products")
	public List<Product> listProducts() {
		List<Product> products = productService.getProducts();
		updateStars(products);
		return products;
	}

	@GetMapping("/products/{id}")
	public Product getProduct(@PathVariable int id) {
		Product product = productService.getProduct(id);
		updateStar(product);
		return product;
	}
	@GetMapping("/product/{name}")
	public Product getProductByName(@PathVariable String name) {
		Product product = productService.getProductByName(name);
		updateStar(product);
		return product;
	}
	@GetMapping("/products/color/{id}")
	public List<Product> getProductsByColor(@PathVariable int id) {
		List<Product> products = filterService.filterByColor(id);
		updateStars(products);
		return products;
	}
	
	@GetMapping("/products/category/{id}")
	public List<Product> getProductsByCategory(@PathVariable int id) {
		List<Product> products = filterService.filterByCategory(id);
		updateStars(products);
		return products;
	}
	@GetMapping("/products/stock")
	public List<Product> getProductsByStock() {
		List<Product> products = filterService.filterByStock();
		updateStars(products);
		return products;
	}
	
	@PostMapping("/products")
	public Response addProduct(@Valid @RequestBody Product product) {
		return productService.saveProduct(product);
	}
	
	@PutMapping("/products")
	public Response updateProduct(@RequestBody Product product) {
		return productService.updateProduct(product);
	}
	
	@DeleteMapping("/products/{id}")
	public Response deleteProduct(@PathVariable int id) {
		return productService.deleteProduct(id);
	}
	public void updateStars(List<Product> products) {
		for(int i = 0 ; i< products.size(); i ++) {
			double sumStars = 0;
			if(!products.get(i).getComments().isEmpty()) {
				for(Comment comment: products.get(i).getComments()) {
					sumStars += comment.getStar();
				}
				sumStars /= products.get(i).getComments().size();
				products.get(i).setStar(sumStars);
			} else {
				products.get(i).setStar(5);
			}
			productService.updateProduct(products.get(i));
		}
	}
	public void updateStar(Product product) {
		double sumStars = 0;
		if(!product.getComments().isEmpty()) {
			for(Comment comment: product.getComments()) {
				sumStars += comment.getStar();
			}
			sumStars /= product.getComments().size();
			product.setStar(sumStars);
		} else {
			product.setStar(5);
		}
		productService.updateProduct(product);
	}
}
