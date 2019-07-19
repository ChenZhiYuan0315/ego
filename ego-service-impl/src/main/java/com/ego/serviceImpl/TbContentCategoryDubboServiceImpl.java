package com.ego.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.ego.mapper.TbContentCategoryMapper;
import com.ego.pojo.TbContentCategory;
import com.ego.pojo.TbContentCategoryExample;
import com.ego.service.TbContentCategoryDubboService;

@Service
@Component
public class TbContentCategoryDubboServiceImpl implements TbContentCategoryDubboService {
	
	@Autowired
	private TbContentCategoryMapper tbContentCategoryMapper;
	/**商品类目
	 * 根据父id查询子类目
	 * @param pid
	 * @return
	 */
	@Override
	public List<TbContentCategory> show(long pid) {
		// TODO Auto-generated method stub
		TbContentCategoryExample example = new TbContentCategoryExample();
		example.createCriteria().andParentIdEqualTo(pid);
		return tbContentCategoryMapper.selectByExample(example);
	}
	 /**
	 * 添加商品类目
	 */
	@Override
	public int InserTbContentCategory(TbContentCategory category) {
		// TODO Auto-generated method stub
		
		return tbContentCategoryMapper.insertSelective(category);
	}
	 /**
	   * 修改商品类目
	   */
	@Override
	public int UpdateTbContentCategory(TbContentCategory category) {
		// TODO Auto-generated method stub
		return tbContentCategoryMapper.updateByPrimaryKeySelective(category);
	}
	/**
	  * 根据主键查询全部
	  */
	@Override
	public TbContentCategory save(long id) {
		// TODO Auto-generated method stub
		return tbContentCategoryMapper.selectByPrimaryKey(id);
	}
	/**
	   * 根据id 删除商品类目
	   */
	@Override
	public int deleteTbContentCategory(long id) {
		// TODO Auto-generated method stub
		return tbContentCategoryMapper.deleteByPrimaryKey(id);
	}

}
