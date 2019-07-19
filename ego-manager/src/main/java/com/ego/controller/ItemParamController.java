package com.ego.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.commons.pojo.EasyUIDataGrid;
import com.commons.pojo.EgoStatus;
import com.ego.pojo.TbItemParam;
import com.ego.service.ItemParamService;

@Controller
public class ItemParamController {

	@Autowired
	private ItemParamService itemParamServiceImpl;

	/**
	 * 查询规格参数并分页
	 */
	@RequestMapping("/item/param/list")
	@ResponseBody
	public EasyUIDataGrid show(int page, int rows) {
		return itemParamServiceImpl.ParamShow(page, rows);
	}

	/**
	 * 根据id来删除规格
	 */
	@RequestMapping("/item/param/delete")
	@ResponseBody
	public EgoStatus delete(String ids) throws Exception {
		EgoStatus status = new EgoStatus();
		int index;
		try {
			index = itemParamServiceImpl.deleItemParam(ids);
			if (index == 1) {
				// 给前台的状态码
				status.setStatus(200);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			// 给前台的异常信息
			status.setExcep(e.toString());
		}

		return status;

	}
	
	/**
	 * 根据类目 catid 查询参数模板  （规格参数的 新增）
	 * 是为了判断是否已经存在这个规格参数，200存在，页面会提示请选择其他
	 */
	@RequestMapping("/item/param/query/itemcatid/{catid}")
	@ResponseBody
	public EgoStatus itemcatid(@PathVariable long catid){
		return itemParamServiceImpl.seleItemParam(catid);
	}
	
	/**
	 * 新添商品规格
	 */
	@RequestMapping("/item/param/save/{catid}")
	@ResponseBody
	public EgoStatus ItemParamSave(@PathVariable long catid,TbItemParam tbItemParam){
		EgoStatus status=new EgoStatus();
		int index = itemParamServiceImpl.insertItemParam(tbItemParam, catid);
		if(index==1){
			status.setStatus(200);
		}
		return status;	
	}
}
