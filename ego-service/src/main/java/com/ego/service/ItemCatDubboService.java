package com.ego.service;

import java.util.List;

import com.ego.pojo.TbItemCat;
import com.ego.pojo.TbItemDesc;

public interface ItemCatDubboService {
/**
 * 根据父ID查询所以子类目
 *
 */
	List<TbItemCat> show(long pid);
	
	/**
	 * 根据Id查小说类目（name）
	 */
	TbItemCat seleshow(long id);
}
