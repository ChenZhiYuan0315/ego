package com.ego.service;

import java.util.List;

import com.commons.pojo.EasyUiTree;
import com.commons.pojo.EgoStatus;
import com.ego.pojo.TbContentCategory;
public interface TbContentCategoryService {
	/**商品类目
	 * 根据父id查询子类目
	 * @param pid
	 * @return
	 */
	List<EasyUiTree> TbContentCategory(long pid);
	/**
	 * 添加商品类目
	 */
	EgoStatus InsertTbContentCategory(TbContentCategory category);
	/**
	 * 修改商品类目的名称,重命名
	 */
	EgoStatus UpdateTbContentCategory(TbContentCategory tb);
	/**
	   * 根据id 删除商品类目
	   */
	EgoStatus deleteTbContentCategory(long id);
}
