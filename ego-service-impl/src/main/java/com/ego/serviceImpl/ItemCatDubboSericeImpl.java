package com.ego.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.ego.mapper.TbItemCatMapper;
import com.ego.pojo.TbItemCat;
import com.ego.pojo.TbItemCatExample;
import com.ego.service.ItemCatDubboService;

@Service
@Component
public class ItemCatDubboSericeImpl implements ItemCatDubboService {
  
	@Autowired
	private TbItemCatMapper tbItemCatMapper;
	
	/**
	 * 根据父ID查询所以子类目
	 *
	 */
	@Override
	public List<TbItemCat> show(long pid) {
		// TODO Auto-generated method stub
		TbItemCatExample example =new TbItemCatExample();
		example.createCriteria().andParentIdEqualTo(pid);
		return tbItemCatMapper.selectByExample(example);
	}

	/**
	 * 根据Id差小说类目（name）
	 */
	@Override
	public TbItemCat seleshow(long id) {
		// TODO Auto-generated method stub
		return tbItemCatMapper.selectByPrimaryKey(id);
	}

}
