package com.ego.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.commons.pojo.EasyUIDataGrid;
import com.ego.mapper.TbItemParamMapper;
import com.ego.pojo.TbItemParam;
import com.ego.pojo.TbItemParamExample;
import com.ego.service.ItemParamDubboService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
@Component
public class ItemParamDubboServiceImpl implements ItemParamDubboService {

	@Autowired
	private TbItemParamMapper tbItemParamMapper;

	/**
	 * 查询规格参数并分页
	 */
	public EasyUIDataGrid ParamShow(int page, int rows) {

		PageHelper.startPage(page, rows);
		List<TbItemParam> list = tbItemParamMapper.selectByExampleWithBLOBs(new TbItemParamExample());
		PageInfo<TbItemParam> pageInfo = new PageInfo<>(list);
		EasyUIDataGrid eGrid = new EasyUIDataGrid();
		eGrid.setRows(pageInfo.getList());
		eGrid.setTotal(pageInfo.getTotal());
		return eGrid;
	}

	/**
	 * 根据id来删除规格
	 */
	@Override
	public int deleItemParam(long id) {

		return tbItemParamMapper.deleteByPrimaryKey(id);
	}

	/**
	 * 根据类目 catid 查询参数模板  （规格参数的 新增）
	 * 是为了判断是否已经存在这个规格参数，200存在，页面会提示请选择其他
	 */
	@Override
	public TbItemParam seleItemParam(long catid) {

		TbItemParamExample example = new TbItemParamExample();
		example.createCriteria().andItemCatIdEqualTo(catid);
		List<TbItemParam> list = tbItemParamMapper.selectByExampleWithBLOBs(example);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	/**
	 * 新添商品规格
	 */
	@Override
	public int insertItemParam(TbItemParam tbItemParam) {
	
		return 	tbItemParamMapper.insertSelective(tbItemParam);
	}

}
