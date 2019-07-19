package com.ego.service;

import java.util.List;

import com.ego.pojo.TbContentCategory;

public interface TbContentCategoryDubboService {
	/**
	 * 根据父id查询子类目
	 * @param pid
	 * @return
	 */
List<TbContentCategory> show(long pid);

 /**
 * 添加商品类目
 */
  int InserTbContentCategory(TbContentCategory category);
  /**
   * 修改商品类目
   */
  int UpdateTbContentCategory(TbContentCategory category);
 /**
  * 根据主键查询全部
  */
  TbContentCategory save(long id);
  /**
   * 根据id 删除商品类目
   */
  int deleteTbContentCategory(long id);
}
