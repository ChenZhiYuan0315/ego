package com.ego.service;

import java.util.List;

import com.commons.pojo.EasyUiTree;

public interface ItemCatService {
/**
 * 根据父id查询全部子类目
 */
	List<EasyUiTree> show(long pid);
}
