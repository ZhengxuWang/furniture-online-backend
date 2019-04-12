package com.mercury.shop.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mercury.shop.bean.Order;
import com.mercury.shop.bean.OrderProduct;
import com.mercury.shop.bean.Product;
import com.mercury.shop.dao.OrderDao;
import com.mercury.shop.dao.OrderProductDao;
import com.mercury.shop.dao.ProductDao;
import com.mercury.shop.dao.UserDao;
import com.mercury.shop.http.Response;
import com.mercury.shop.security.SecurityUtils;

@Service

public class OrderService {

	@Autowired
	OrderDao orderDao;

	@Autowired
	ProductDao productDao;
	
	@Autowired
	OrderProductDao orderProductDao;
	
	@Autowired
	UserDao userDao;
	
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public Response addOrder(Order order, Authentication authentication) {
		try {
			List<OrderProduct> purchases = order.getPurchases();
			purchases.forEach((orderProduct) -> {
				//enrich
				Product product = (Product) productDao.findById(orderProduct.getProduct().getId()).get();
				orderProduct.setProduct(product);
				orderProduct.setOrder(order);
			});
			//User user = (User)userDao.findById(order.getUser().getId()).get();
			//order.setUser(user);
			order.setUser(userDao.findByUsername(authentication.getName()));
			orderDao.save(order);
			return new Response(true);
		} catch (Exception e) {
			return new Response(false);
		}
	}
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public Response editOrder(Order order) {
		try {
			Order o = (Order) orderDao.findById(order.getId()).get();
			
			List<OrderProduct> purchasesToRemove = o.getPurchases();
			System.out.println(purchasesToRemove);
			List<OrderProduct> purchases = order.getPurchases();
			System.out.println(purchases);
			// handled update and add products in order
			purchases.forEach((orderProduct) -> {
				Product product = (Product) productDao.findById(orderProduct.getProduct().getId()).get();
				orderProduct.setProduct(product);
				orderProduct.setOrder(o);
			});
			System.out.println(purchases);
			// handle remove products in order
			if(purchases.size() > 0) {
				purchasesToRemove = purchasesToRemove.stream().filter((orderProduct) -> {
					return !purchases.contains(orderProduct);
				}).collect(Collectors.toList());
			}
			System.out.println(purchasesToRemove);
			o.setPurchases(purchases);
			orderDao.save(o);
			
			deleteOrderProducts(purchasesToRemove);	
						
			return new Response(true);
		} catch (Exception e) {
			System.out.println(e);
			return new Response(false);
		}
	}
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public void deleteOrderProducts(List<OrderProduct> purchases) {
		orderProductDao.deleteAll(purchases);	
	}
	public List<Order> getOrders(Authentication authentication) {
		if(SecurityUtils.isAdmin(authentication.getAuthorities())) {
			return (List<Order>)orderDao.findAll();
		} else {
			System.out.println(authentication.getName());
			return orderDao.findByUserId(userDao.findByUsername(authentication.getName()).getId());
		}
	}
	

}
