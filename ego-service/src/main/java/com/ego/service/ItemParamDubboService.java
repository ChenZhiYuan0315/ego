package com.ego.service;

import com.commons.pojo.EasyUIDataGrid;
import com.ego.pojo.TbItemParam;

public interface ItemParamDubboService {
	/**
	 * 查询规格参数并分页
	 */
	EasyUIDataGrid ParamShow(int page,int rows);
	
	/**
	 * 根据id来删除规格
	 */
	int deleItemParam(long id);
	
	/**
	 * 根据类目 catid 查询参数模板  （规格参数的 新增）
	 */
	TbItemParam seleItemParam(long catid);
	
	/**
	 * 新添商品规格
	 */
	int insertItemParam(TbItemParam tbItemParam);
}
