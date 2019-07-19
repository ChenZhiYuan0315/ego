package com.ego.service;

import com.commons.pojo.EasyUIDataGrid;
import com.commons.pojo.EgoStatus;
import com.ego.pojo.TbItemParam;

public interface ItemParamService {
	/**
	 * 查询规格参数并分页
	 */
  EasyUIDataGrid ParamShow(int page,int rows);
  
     /**
	 * 根据id来删除规格
	 */
   int deleItemParam(String ids) throws Exception;
   
   /**
	 * 根据类目 catid 查询参数模板  （规格参数的 新增）
	 * 是为了判断是否已经存在这个规格参数，200存在，页面会提示请选择其他
	 */
     EgoStatus seleItemParam(long catid);
     
     /**
 	 * 新添商品规格
 	 */
     int insertItemParam(TbItemParam tbItemParam,long catid);
}
