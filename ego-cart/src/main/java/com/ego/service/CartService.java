package com.ego.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.commons.pojo.TbItemChild;

public interface CartService {
	/**
	 * 把购物车的信息存进redis中
	 */
 void CartRedis(long id,int num, HttpServletRequest request);
 
 /**
  * 显示购物车信息
  */
 List<TbItemChild> showcart(HttpServletRequest request);
 /**
	 * 商品的增加和减少
	 */
  int cartupdate(long id,int num,HttpServletRequest request);
  
  /**
	 * 商品的删除
	 */
  int CartDelete(long id,HttpServletRequest request);
  
}
