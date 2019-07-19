package com.ego.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.ego.service.TbContentService;


@Controller
public class TbContentController {

	@Autowired
	private TbContentService tbContentServiceImpl;
	
	@RequestMapping("/showBigPic")
	public String pic(Model model){
		String show = tbContentServiceImpl.show();
		System.out.println(show);
		model.addAttribute("ad1",show );
		return "index";
		
	}
}
