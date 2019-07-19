package com.ego.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.commons.pojo.EgoStatus;
import com.ego.pojo.TbUser;
import com.ego.service.TbUserService;
import com.ego.serviceImpl.TbUserServiceImpl;

@Controller
public class LoginController {
     @Autowired
     private TbUserService tbUserServiceImpl;

	@RequestMapping("/user/showLogin")
	public String showLogin(@RequestHeader("Referer") String url,Model model){
		if(url.equals("http://localhost:8084/user/showRegister")){
			url="";
		}
		model.addAttribute("redirect", url);
		//
		return "login";
	}
	
	/**
	 * 校验用户名和密码
	 * @param user
	 * @return
	 */
	@RequestMapping("/user/login")
	@ResponseBody
	public EgoStatus login(TbUser user,HttpServletRequest request,HttpServletResponse response){
		return tbUserServiceImpl.login(user, request, response);
		
	}
	
	/**
	 * 实现单点登入
	 */
	@RequestMapping("/user/token/{token}")
	@ResponseBody
	public Object getUserInfo(@PathVariable String token,String callback){
		EgoStatus Status = tbUserServiceImpl.getUserInfo(token);
		if(callback!=null){
			MappingJacksonValue jack=new MappingJacksonValue(Status);
			jack.setJsonpFunction(callback);
			return jack;
		}
		return Status;
		
	}
	/**
	 * 退出登入
	 */
	@RequestMapping("/user/logout/{token}")
	@ResponseBody
	public Object logout(@PathVariable String token,HttpServletRequest request,HttpServletResponse response,String callback){
		EgoStatus Status = tbUserServiceImpl.logout(token, request, response);
		if(callback!=null){
			MappingJacksonValue jack=new MappingJacksonValue(Status);
			jack.setJsonpFunction(callback);
			return jack;
		}
		return Status;

		
	}
}
