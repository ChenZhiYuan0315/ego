package com.ego.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.commons.pojo.EasyUiTree;
import com.commons.pojo.EgoStatus;
import com.commons.utils.IDUtils;
import com.ego.service.TbContentCategoryDubboService;
import com.ego.service.TbContentCategoryService;
import com.ego.pojo.TbContentCategory;

@Service
public class TbContentCategoryServiceImpl implements TbContentCategoryService {

	@Reference
	private TbContentCategoryDubboService tbContentCategoryDubboServiceImpl;
	/**商品类目
	 * 根据父id查询子类目 
	 * @param pid
	 * @return
	 */
	@Override
	public List<EasyUiTree> TbContentCategory(long pid) {
	    List<TbContentCategory> show = tbContentCategoryDubboServiceImpl.show(pid);
		List<EasyUiTree> list = new ArrayList<>();
		for (TbContentCategory cate : show) {
			EasyUiTree easyUiTree = new EasyUiTree();
			easyUiTree.setId(cate.getId());
			easyUiTree.setText(cate.getName());
			easyUiTree.setState(cate.getIsParent()? "closed" : "open");
			list.add(easyUiTree);
		}
		// TODO Auto-generated method stub
		return list;
	}
	/**
	 * 添加商品类目
	 */
	@Override
	public EgoStatus InsertTbContentCategory(TbContentCategory category) {
		// TODO Auto-generated method stub
		Date date=new Date();
		EgoStatus status=new EgoStatus();
	
		List<TbContentCategory> show = tbContentCategoryDubboServiceImpl.show(category.getParentId());
		for (TbContentCategory tbc : show) {
			//判断添加的内容分类名称是否存在
			if(tbc.getName().equals(category.getName())){
			   status.setData("原因：分支名称已存在");
		       return status;	
			}
			}
			category.setCreated(date);
			category.setUpdated(date);
			category.setStatus(1);
			category.setSortOrder(1);
			category.setIsParent(false);
			int index = tbContentCategoryDubboServiceImpl.InserTbContentCategory(category);
			/**
			 * 当子类目添加类目成功后，子类目变成父类目,根据id修改is_parent
			 */
			if(index==1){
				TbContentCategory tb=new TbContentCategory();
				tb.setId(category.getParentId());
				tb.setIsParent(true);
				tbContentCategoryDubboServiceImpl.UpdateTbContentCategory(tb);
				status.setStatus(200);
				status.setData(category.getId());
			}
			return status;
		}
	/**
	 * 修改商品类目名称,重命名
	 */
	@Override
	public EgoStatus UpdateTbContentCategory(TbContentCategory tb) {
		// TODO Auto-generated method stub
		EgoStatus status=new EgoStatus();
		/**
		 * 根据传过来的Id,取出parentId，并根据parentId去查询子类目,判断更改的名字是否已经存在
		 */
		Long id = tb.getId();
		TbContentCategory save = tbContentCategoryDubboServiceImpl.save(id);
		List<TbContentCategory> show = tbContentCategoryDubboServiceImpl.show(save.getParentId());
		for (TbContentCategory tbc : show) {
			if(tbc.getName().equals(tb.getName())){
				status.setData("原因：分支名称已存在");
				return status;
			}
		}
		int index = tbContentCategoryDubboServiceImpl.UpdateTbContentCategory(tb);
		if(index==1){
			status.setStatus(200);
		}
		return status;
	}
	/**
	   * 根据id 删除商品类目
	   */
	@Override
	public EgoStatus deleteTbContentCategory(long id) {
		// TODO Auto-generated method stub
		EgoStatus status=new EgoStatus();
		
		TbContentCategory save = tbContentCategoryDubboServiceImpl.save(id);
		Long parentId = save.getParentId();
		/**
		 * 根据主键删除
		 */
		int index = tbContentCategoryDubboServiceImpl.deleteTbContentCategory(id);
		if(index==1){
			status.setStatus(200);
		}
		/**
		 * 根据id查询全部数据，取出ParentId查询有关的子类目，如果没有子类目，则修改父类类目的 is_parent值
		 */
		List<TbContentCategory> show = tbContentCategoryDubboServiceImpl.show(parentId);
		if(show==null||show.size()==0){
			TbContentCategory tb=new TbContentCategory();
			tb.setId(parentId);
			tb.setIsParent(false);
			tbContentCategoryDubboServiceImpl.UpdateTbContentCategory(tb);
		}
		
		return status;
	}
	
	
	
	
		}


