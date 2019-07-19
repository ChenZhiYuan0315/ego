package com.ego.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.commons.pojo.EasyUIDataGrid;
import com.ego.mapper.TbContentMapper;
import com.ego.pojo.TbContent;
import com.ego.pojo.TbContentExample;
import com.ego.service.TbContentDubboService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
@Component
public class TbContentDubboServiceImpl implements TbContentDubboService{

	@Autowired
	private TbContentMapper tbContentMapper;
	/**
	 * 内容管理查询并分页  
	 * @param age 第一页
	 * @param rows 每页显示33条数据
	 * @return
	 */
	@Override
	public EasyUIDataGrid show(long categoryId, int page, int rows) {
		// TODO Auto-generated method stub
		EasyUIDataGrid easy=new EasyUIDataGrid();
		PageHelper.startPage(page, rows);
		TbContentExample example=new TbContentExample();
		if(categoryId!=0){
			example.createCriteria().andCategoryIdEqualTo(categoryId);
		}
		List<TbContent> list = tbContentMapper.selectByExample(example);
		PageInfo<TbContent> info=new PageInfo<>(list);
		easy.setRows(info.getList());
		easy.setTotal(info.getTotal());
		return easy;
	}
	/**
	 * 内容管理的新增
	 */
	@Override
	public int insertTbContent(TbContent tbContent) {
		// TODO Auto-generated method stub
		return tbContentMapper.insertSelective(tbContent);
	}
	/**
	 * 内容管理的删除
	 * 根据主键删除
	 */
	@Override
	public int deleteTbContent(long id) {
		// TODO Auto-generated method stub
		return tbContentMapper.deleteByPrimaryKey(id);
	}
	/**
	 * 修改
	 */
	@Override
	public int updateTbContent(TbContent tbContent) {
		// TODO Auto-generated method stub
		return tbContentMapper.updateByPrimaryKey(tbContent);
	}
	/**
	 * 返回前端的大广告，查全部
	 * count表示大广告出现几个，issort表示是否排序
	 */
	@Override
	public List<TbContent> selectTbContent(int count, boolean issort) {
		// TODO Auto-generated method stub
		TbContentExample example=new TbContentExample();
		if(issort){
			  example.setOrderByClause("updated desc");	
			}
		if(count!=0){
			PageHelper.startPage(1, count);
			List<TbContent> list= tbContentMapper.selectByExample(example);
			PageInfo<TbContent> info=new PageInfo<>(list);
			return info.getList();
		}
		
		return tbContentMapper.selectByExample(example);
	}

}
