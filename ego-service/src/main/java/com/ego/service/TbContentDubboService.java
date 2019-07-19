package com.ego.service;

import java.util.List;

import com.commons.pojo.EasyUIDataGrid;
import com.ego.pojo.TbContent;

public interface TbContentDubboService {
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
	int insertTbContent(TbContent tbContent);
	/**
	 * 内容管理的删除
	 * 根据主键删除
	 */
	int deleteTbContent(long id);
	/**
	 * 修改
	 */
	int updateTbContent(TbContent tbContent);
	/**
	 * 返回前端的大广告，查全部
	 * count表示大广告出现几个，issort表示是否排序
	 */
	List<TbContent> selectTbContent(int count,boolean issort);
}
