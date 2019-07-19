package com.ego.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.commons.pojo.EasyUIDataGrid;
import com.commons.pojo.EgoStatus;
import com.ego.pojo.EgoItemParam;
import com.ego.pojo.TbItemParam;
import com.ego.service.ItemCatDubboService;
import com.ego.service.ItemParamDubboService;
import com.ego.service.ItemParamService;

@Service
public class ItemParamServiceImpl implements ItemParamService {

	@Reference
	private ItemParamDubboService itemParamDubboServiceImpl;

	@Reference
	private ItemCatDubboService itemCatDubboServiceImpl;

	/**
	 * 查询规格参数并分页
	 */
	@Override
	public EasyUIDataGrid ParamShow(int page, int rows) {

		EasyUIDataGrid show = itemParamDubboServiceImpl.ParamShow(page, rows);

		List<EgoItemParam> param = new ArrayList<>();
		List<TbItemParam> list = (List<TbItemParam>) show.getRows();
		for (TbItemParam tb : list) {
			EgoItemParam ego = new EgoItemParam();
			ego.setId(tb.getId());
			ego.setCreated(tb.getCreated());
			ego.setItemCatId(tb.getItemCatId());
			ego.setParamData(tb.getParamData());
			ego.setUpdated(tb.getUpdated());
			ego.setItemCatName(itemCatDubboServiceImpl.seleshow(tb.getItemCatId()).getName());
			param.add(ego);
		}
		show.setRows(param);
		return show;
	}

	/**
	 * 根据id来删除规格
	 */
	@Override
	public int deleItemParam(String ids) throws Exception {
		String[] split = ids.split(",");
		int index = 0;
		for (String str : split) {
			long id = Long.parseLong(str);
			index  += itemParamDubboServiceImpl.deleItemParam(id);
		}
		if (split.length == index) {
			return 1;
		} else {
			throw new Exception("错误原因：该参数已经被参数");
		}
	}

	/**
	 * 根据类目 catid 查询参数模板  （规格参数的 新增）
	 * 是为了判断是否已经存在这个规格参数，200存在，页面会提示请选择其他
	 */
	@Override
	public EgoStatus seleItemParam(long catid) {
       EgoStatus status=new EgoStatus();
		TbItemParam tbItemParam = itemParamDubboServiceImpl.seleItemParam(catid);
		if(tbItemParam!=null){
			status.setStatus(200);
			status.setData(tbItemParam.getParamData());
		}
		return status;
	}

	/**
	 * 新添商品规格
	 */
	@Override
	public int insertItemParam(TbItemParam tbItemParam, long catid) {
		Date date=new Date();
		tbItemParam.setItemCatId(catid);
		tbItemParam.setUpdated(date);
		tbItemParam.setCreated(date);
		int index = itemParamDubboServiceImpl.insertItemParam(tbItemParam);
		return index;
	}

}
