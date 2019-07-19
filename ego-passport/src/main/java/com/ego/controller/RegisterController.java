package com.ego.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.commons.pojo.EgoStatus;
import com.ego.pojo.TbUser;
import com.ego.service.TbUserService;
import com.ego.serviceImpl.TbUserServiceImpl;

@Controller
public class RegisterController {

    @Autowired
     private TbUserService tbUserServiceImpl;
	

	@RequestMapping("/user/showRegister")
	public String showRegister(){
		return "register";
		
	}
	
	/**
	 * 注册,检查用户名是否存在
	 * @param r
	 * @param usernane
	 * @return
	 */
	@RequestMapping("/user/check/{username}/1")
	@ResponseBody
	public EgoStatus check(@PathVariable Object username){
		EgoStatus status = tbUserServiceImpl.SelectName(username.toString());
		return status;
		
	}
	
	/**
	 * 注册,检查手机号是否存在
	 * @param r
	 * @param usernane
	 * @return
	 */
	@RequestMapping("/user/check/{phone}/2")
	@ResponseBody
	public EgoStatus check2(@PathVariable String phone){
		EgoStatus status = tbUserServiceImpl.SelectPhone(phone);
		return status;	
	}
	
	/**
	 * 注册
	 */
	@RequestMapping("/user/register")
	@ResponseBody
	public EgoStatus register(TbUser user){
		return tbUserServiceImpl.InsertUser(user);
		
	}
}
