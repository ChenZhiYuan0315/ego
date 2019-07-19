package com.ego.service;

import com.commons.pojo.EasyUIDataGrid;
import com.commons.pojo.EgoStatus;
import com.ego.pojo.TbContent;

public interface TbContentService {
	/**
	 * 内容管理查询并分页  
	 * @param age 第一页
	 * @param rows 每页显示33条数据
	 * @return
	 */
	EasyUIDataGrid show(long categoryId,int page,int rows);
	/**
	 * 内容管理的新增
	 */
	EgoStatus insertTbContent(TbContent tbContent);
	/**
	 * 内容管理的删除
	 * 根据主键删除
	 */
	EgoStatus deleteTbContent(String ids);
	/**
	 * 内容管理的修改
	 */
	EgoStatus updateTbContent(TbContent tbContent);
}
