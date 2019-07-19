package com.ego.service;

import java.util.List;

import com.ego.pojo.TbUser;

public interface TbUserDubboService {

	/**
	 * 校验用户名和密码
	 * @param user
	 * @return
	 */
	TbUser login(TbUser user);
	
	/**
	 * 注册 根据用户名查数据
	 */
	List<TbUser> SelectName(String username);
	
	/**
	 * 注册 根据手机号查数据
	 */
	List<TbUser> SelectPhone(String phone);
	
	/**
	 * 注册
	 */
	int insertUser(TbUser user);
}
