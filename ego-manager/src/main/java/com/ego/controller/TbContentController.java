package com.ego.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.commons.pojo.EasyUIDataGrid;
import com.commons.pojo.EgoStatus;
import com.ego.pojo.TbContent;
import com.ego.service.TbContentService;

@Controller
public class TbContentController {

	@Autowired
	private TbContentService tbContentServiceImpl;
	
	@RequestMapping("/content/query/list")
	@ResponseBody
	public EasyUIDataGrid list(long categoryId,int page,int rows){
		return tbContentServiceImpl.show(categoryId, page, rows);
	}
	
	/**
	 * 内容管理的新增
	 */
	@RequestMapping("/content/save")
	@ResponseBody
	public EgoStatus save(TbContent tbContent){
		return tbContentServiceImpl.insertTbContent(tbContent);
		
	}
	/**
	 * 内容管理的删除
	 * 根据主键删除
	 */
	@RequestMapping("/content/delete")
	@ResponseBody
	public EgoStatus delete(String ids){
		return tbContentServiceImpl.deleteTbContent(ids);
		
	}
	/**
	 * 内容管理的修改
	 */
	@RequestMapping("/rest/content/edit")
	@ResponseBody
	public EgoStatus edit(TbContent tbContent){
		return tbContentServiceImpl.updateTbContent(tbContent);
		
	}
}
