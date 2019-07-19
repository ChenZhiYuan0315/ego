package com.ego.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.commons.pojo.EgoStatus;
import com.ego.pojo.TbUser;

public interface TbUserService {
	/**
	 * 校验用户名和密码
	 * @param user
	 * @return
	 */
	EgoStatus login(TbUser user,HttpServletRequest request,HttpServletResponse response);
	
	/**
	 * 单点登入
	 */
	EgoStatus getUserInfo(String token);
	
	/**
	 * 退出登入
	 */
	EgoStatus logout(String token,HttpServletRequest request,HttpServletResponse response);
	/**
	 * 根据用户名查数据
	 */
	EgoStatus SelectName(String username);
	/**
	 * 根据手机号查数据
	 */
	EgoStatus SelectPhone(String phone);
	/**
	 * 注册
	 */
	EgoStatus InsertUser(TbUser user);
}
