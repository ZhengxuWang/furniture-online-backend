package com.mercury.shop.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mercury.shop.bean.Order;
import com.mercury.shop.dao.OrderDao;
import com.mercury.shop.http.Response;
import com.mercury.shop.jms.OrdersReportProducer;
import com.mercury.shop.service.OrderService;

@RestController
public class OrderController {
	
	@Autowired
	OrderDao orderDao;
	
	@Autowired
	OrderService orderService;
	@Autowired
	OrdersReportProducer ordersReportProducer;

	@GetMapping("/orders")
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER')")
	public List<Order> getOrders(Authentication authentication) {
		return orderService.getOrders(authentication);
	}
	
	@GetMapping("/orders/{id}")
	public Order getOrder(@PathVariable int id) {
		return orderDao.findById(id).get();
	}
	
	public void printOrders() {
		System.out.println(Arrays.asList((List<Order>) orderDao.findAll()));
	}
	
	@PostMapping("/orders")
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER')")
	public Response postOrders(@RequestBody Order order, Authentication authentication) {
		return orderService.addOrder(order, authentication);
	}
	
	@PutMapping("/orders")
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER')")
	public Response putOrders(@RequestBody Order order) {
		return orderService.editOrder(order);
	}
	@GetMapping("/orders_report")
	public void getOrdersReport(Authentication authentication) {
		ordersReportProducer.sendOrdersForReport(authentication, getOrders(authentication));
	}
}