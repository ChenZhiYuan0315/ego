package com.ego.serviceImpl;

import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ego.service.ItemDescDubboService;
import com.ego.service.ItemDescService;

@Service
public class ItemDescServiceImpl implements ItemDescService {

	@Reference
	private ItemDescDubboService itemDescDubboServiceImpl;

	/**
	 * 显示商品描述
	 * @param id
	 * @return
	 */
	@Override
	public String selectDesc(long id) {
		// TODO Auto-generated method stub
		return itemDescDubboServiceImpl.selectdesc(id).getItemDesc();
	}
}
