package com.ego.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.commons.pojo.EasyUiTree;
import com.ego.service.ItemCatService;

@Controller
public class ItemCatController {

	@Autowired
	private ItemCatService itemCatServiceImpl;
	
	@RequestMapping("/item/cat/list")
	@ResponseBody
	public List<EasyUiTree> show(@RequestParam(defaultValue="0") long id){
		List<EasyUiTree> show = itemCatServiceImpl.show(id);
		return show;
	}
}
