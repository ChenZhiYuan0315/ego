package com.ego.serviceImpl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.commons.pojo.EasyUIDataGrid;
import com.commons.pojo.EgoStatus;
import com.ego.pojo.TbContent;
import com.ego.service.TbContentDubboService;
import com.ego.service.TbContentService;

@Service
public class TbContentServiceImpl implements TbContentService {
 
	@Reference
	private TbContentDubboService tbContentDubboServiceImpl;
	
	@Autowired
	RedisTemplate<String,Object> redis;
	/**
	 * 内容管理查询并分页  
	 * @param age 第一页
	 * @param rows 每页显示33条数据
	 * @return
	 */
	@Override
	public EasyUIDataGrid show(long categoryId, int page, int rows) {
		// TODO Auto-generated method stub
		return tbContentDubboServiceImpl.show(categoryId, page, rows);
	}
	/**
	 * 内容管理的新增
	 */
	@Override
	public EgoStatus insertTbContent(TbContent tbContent) {
		// TODO Auto-generated method stub
		EgoStatus status=new EgoStatus();
		Date data=new Date();
		tbContent.setCreated(data);
		tbContent.setUpdated(data);
		int index = tbContentDubboServiceImpl.insertTbContent(tbContent);
		if(index==1){
			//清空redis中的缓存
			redis.opsForValue().set("tbcontent","");
			status.setStatus(200);
		}
		return status ;
	}
	/**
	 * 内容管理的删除
	 * 根据主键删除
	 */
	@Override
	public EgoStatus deleteTbContent(String ids) {
		// TODO Auto-generated method stub
		String[] split = ids.split(",");
		EgoStatus status=new EgoStatus();
		int index =0;
		for (String str : split) {
			long id=Long.parseLong(str);
			index += tbContentDubboServiceImpl.deleteTbContent(id);
		}
		if(index==split.length){
			//清空redis中的缓存
			redis.opsForValue().set("tbcontent","");
			status.setStatus(200);
			return status;
		}
		status.setData("错误原因：可能已被删除");
		return status;
		
	}
	/**
	 * 内容管理的修改
	 */
	@Override
	public EgoStatus updateTbContent(TbContent tbContent) {
		// TODO Auto-generated method stub
		Date date=new Date();
		EgoStatus status=new EgoStatus();
		tbContent.setUpdated(date);
		tbContent.setCreated(date);
		int index = tbContentDubboServiceImpl.updateTbContent(tbContent);
		if(index==1){
			//清空redis中的缓存
			redis.opsForValue().set("tbcontent","");
			status.setStatus(200);
		}
		return status;
	}

}
