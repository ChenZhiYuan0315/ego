package com.ego.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.ego.mapper.TbItemDescMapper;
import com.ego.pojo.TbItemDesc;
import com.ego.service.ItemDescDubboService;

@Service
@Component
public class ItemDescDubboServiceImpl implements ItemDescDubboService{
 
	@Autowired
	private TbItemDescMapper tbItemDescMapper;
	@Override
	public TbItemDesc selectdesc(long id) {
		// TODO Auto-generated method stub
		return tbItemDescMapper.selectByPrimaryKey(id);
	}

}
