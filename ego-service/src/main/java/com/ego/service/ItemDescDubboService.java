package com.ego.service;

import com.ego.pojo.TbItemDesc;

public interface ItemDescDubboService {
/**
 * 根据item id查询商品描述
 */
	TbItemDesc selectdesc(long id);
}
