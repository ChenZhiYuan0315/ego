package com.ego.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.commons.pojo.TbItemChild;
import com.ego.pojo.MyOrder;
import com.ego.service.OrderService;
import com.ego.serviceImpl.OrderServiceImpl;

@Controller
public class OrderController { 
@Autowired
private OrderService orderServiceImpl;


	@RequestMapping("/order/order-cart.html")
	public String ordercat(@RequestParam("id")List<String> ids,Model model,HttpServletRequest request){
		System.out.println(ids);
		List<TbItemChild> show = orderServiceImpl.show(ids, request);
		model.addAttribute("cartList", show);
	
		return "order-cart";
	}
	
	/**
	 * 提交订单
	 */
	@RequestMapping("/order/create.html")
	public String OrderCreate(MyOrder myOrder,HttpServletRequest request){
		System.out.println(myOrder);
		orderServiceImpl.OrderCreate(myOrder, request);
		return "my-orders";
		
	}
}
