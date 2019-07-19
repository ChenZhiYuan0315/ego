package com.ego.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.commons.pojo.EasyUIDataGrid;
import com.ego.service.ItemDubboService;


@Controller
public class Test {

	@Autowired
	private ItemDubboService ItemDubooService;
	
	@RequestMapping("/test")
	@ResponseBody
	public EasyUIDataGrid test(){
		
		EasyUIDataGrid show = ItemDubooService.show(1, 33);
		return show;
	}
} 
