package com.ego.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.commons.pojo.EgoStatus;
import com.ego.service.CartService;
import com.ego.serviceImpl.CartServiceImpl;

@Controller
public class CartController {

	@Autowired
	private CartService cartServiceImpl;
	/**
	 * 加入购物车
	 * @param id
	 * @param num
	 * @return
	 */
	@RequestMapping("/cart/add/{id}.html")
	public String cartadd(@PathVariable long id,int num,HttpServletRequest request){
		cartServiceImpl.CartRedis(id, num, request);
		return "cartSuccess";
	}
	/**
	 * 结算购物车
	 */
	@RequestMapping("/cart/cart.html")
	public String cart(HttpServletRequest request,Model model){
		model.addAttribute("cartList", cartServiceImpl.showcart(request));
		return "cart";
	}
	/**
	 * 增加和减少选购商品
	 */
	@RequestMapping("/cart/update/num/{id}/{num}.action")
	@ResponseBody
	public String cartupdate(@PathVariable long id,@PathVariable int num,HttpServletRequest request){
		cartServiceImpl.cartupdate(id, num, request);
		return null;
		
	}
	/**
	 * 商品的删除
	 */
	@RequestMapping("/cart/delete/{id}.action")
	@ResponseBody
	public EgoStatus cartdelete(@PathVariable long id,HttpServletRequest request){
		EgoStatus status=new EgoStatus();
		int index = cartServiceImpl.CartDelete(id, request);
		if (index==1) {
			status.setStatus(200);
		}
		
		return status;

	}
}
