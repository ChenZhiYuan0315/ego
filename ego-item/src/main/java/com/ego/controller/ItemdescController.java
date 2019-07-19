package com.ego.controller;

import java.util.function.LongFunction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ego.service.ItemDescService;

@Controller
public class ItemdescController {

	@Autowired
	private ItemDescService itemDescServiceImpl;
	/**
	 * 显示商品描述
	 * @param id
	 * @return
	 */
	@RequestMapping("/item/desc/{id}.html")
	@ResponseBody
	public String selectDesc(@PathVariable long id){
		return itemDescServiceImpl.selectDesc(id);
		
	}
}
