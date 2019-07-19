package com.ego.service;

import java.util.List;

import com.commons.pojo.EasyUIDataGrid;
import com.ego.pojo.TbItem;
import com.ego.pojo.TbItemDesc;

public interface ItemDubboService {
	/**
	 * 商品全部查询并分页  
	 * @param age 第一页
	 * @param rows 每页显示33条数据
	 * @return
	 */
	  EasyUIDataGrid show(int age,int rows);
	  
	  /**
	   * 修改商品 
	   */
	  int updItemStaus(TbItem tbItem);
	  
	  /**
	   * 多表新增商品 item 和 item_desc
	   */
	  int inserItemAndItemdesc(TbItem item,TbItemDesc itemDesc) throws Exception;
	  
	  /**
	   * 查询全部status为1的商品
	   */
	  List<TbItem> seAll(byte status);
	  
	  /**
	   * 根据主键查询全部
	   */
	  TbItem showAll(long id);
}
