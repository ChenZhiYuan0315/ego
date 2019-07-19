package com.ego.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.commons.pojo.EasyUiTree;
import com.commons.pojo.EgoStatus;
import com.ego.pojo.TbContentCategory;
import com.ego.service.TbContentCategoryService;

@Controller
public class TbContentCategoryController {

	@Autowired
	private TbContentCategoryService tbContentCategoryServiceImpl;
	
	/**
	 * 根据父id查询子类目
	 * @param id 
	 * @return
	 */
	@RequestMapping("/content/category/list")
	@ResponseBody
	public List<EasyUiTree> CategoryList(@RequestParam(defaultValue="0") long id){
		return tbContentCategoryServiceImpl.TbContentCategory(id);
	}
	/**
	 * 添加内容分类
	 */
	@RequestMapping("/content/category/create")
	@ResponseBody
	public EgoStatus create(TbContentCategory category){
		return tbContentCategoryServiceImpl.InsertTbContentCategory(category);
		
	}
	/**
	 * 修改内容分类的名字
	 */
	@RequestMapping("/content/category/update")
	@ResponseBody
	public EgoStatus update(TbContentCategory tb){
		return tbContentCategoryServiceImpl.UpdateTbContentCategory(tb);
		
	}
	/**
	   * 根据id 删除商品类目
	   */
	@RequestMapping("/content/category/delete")
	@ResponseBody
	public EgoStatus delete(long id){
		return tbContentCategoryServiceImpl.deleteTbContentCategory(id);
		
	}
}
