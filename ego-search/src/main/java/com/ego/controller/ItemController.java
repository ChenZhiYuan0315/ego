package com.ego.controller;

import java.io.IOException;
import java.util.Map;

import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ego.pojo.TbItem;
import com.ego.service.ItemSearchService;


@Controller
public class ItemController {

	@Autowired
     private ItemSearchService itemSearchService;
	
	@RequestMapping("/init")
	@ResponseBody
	public String search(){
		
		try {
			itemSearchService.init();
			return "初始化成功";
		} catch (SolrServerException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "初始化失败";
		}
	}
	
	 /**
	  * 实现搜索功能
	  * page 从哪页开始
	  * rows 当前页显示多少个
	  */
	@RequestMapping("search.html")
	public String search(Model model,String q,@RequestParam(defaultValue="1") int page,@RequestParam(defaultValue="12") int rows){
		try {
			Map<String, Object> map = itemSearchService.SolrSearchAll(q, page, rows);
			model.addAttribute("query", q);
			model.addAttribute("itemList", map.get("list"));
			model.addAttribute("totalPages", map.get("totalPages"));
			model.addAttribute("page", page);
		} catch (Exception e) { 
			e.printStackTrace();
		} 
		return "search";
	}
	
//	@RequestMapping("/search")
//	public String show(){
//		return "forward:/search.html";
//	}
	 /**
	  * 跟后台同步新增
	  */
	@RequestMapping("/solr/add")
	@ResponseBody
	public int add(@RequestBody Map<String,Object> map){

		try {
		
			return itemSearchService.solrInsert((Map<String, Object>) map.get("tbItem"),map.get("desc").toString());
		} catch (SolrServerException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
		
	}
}
