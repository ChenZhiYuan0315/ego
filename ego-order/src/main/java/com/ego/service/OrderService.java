package com.ego.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.commons.pojo.EgoStatus;
import com.commons.pojo.TbItemChild;
import com.ego.pojo.MyOrder;

public interface OrderService {
/**
 * 把购物车的信息显示在订单系统里
 * @param ids
 * @return
 */
	List<TbItemChild> show(List<String> ids,HttpServletRequest request);
	/**
	 * 提交订单
	 */
	int OrderCreate(MyOrder myOrder,HttpServletRequest request);
}
