package com.ego.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.commons.pojo.EasyUiTree;
import com.ego.pojo.TbItemCat;
import com.ego.service.ItemCatDubboService;
import com.ego.service.ItemCatService;

@Service
public class ItemCatServiceImpl implements ItemCatService{

	@Reference
	private ItemCatDubboService itemCatDubboServiceImpl;
	
	/**
	 * 根据父id查询全部子类目 
	 */ 
	@Override
	public List<EasyUiTree> show(long pid) {
		// TODO Auto-generated method stub
		List<TbItemCat> list = itemCatDubboServiceImpl.show(pid);
		
		List<EasyUiTree> trees=new ArrayList<>();
		
		for (TbItemCat tb : list) {
			EasyUiTree easyUiTree=new EasyUiTree();
			easyUiTree.setId(tb.getId());
			easyUiTree.setText(tb.getName());
			easyUiTree.setState(tb.getIsParent() ? "closed":"open");
			
			trees.add(easyUiTree);
		}
		return trees;
	}

}
