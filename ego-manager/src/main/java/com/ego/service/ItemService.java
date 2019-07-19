package com.ego.service;

import com.commons.pojo.EasyUIDataGrid;
import com.ego.pojo.TbItem;
import com.ego.pojo.TbItemDesc;

public interface ItemService {
	
	/**
	 * 查询全部并分页
	 */
     EasyUIDataGrid show(int age,int rows);
     
     /**
 	 * 修改商品
 	 */
     int updItemStaus(String ids,byte status);
     
     /**
	   * 多表新增商品 item 和 item_desc
	   */
      int inserItemAndItemdesc(TbItem item,String desc) throws Exception;
}
