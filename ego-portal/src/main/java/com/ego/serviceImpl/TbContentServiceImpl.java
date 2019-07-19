package com.ego.serviceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import com.alibaba.dubbo.config.annotation.Reference;
import com.commons.utils.JsonUtils;
import com.ego.pojo.TbContent;
import com.ego.service.TbContentDubboService;
import com.ego.service.TbContentService;

@Service
public class TbContentServiceImpl implements TbContentService{


	@Autowired
	private RedisTemplate<String,Object> redis;
	
	@Reference
	private TbContentDubboService tbContentDubboServiceImpl;
	@Override
	public String show() {
		// TODO Auto-generated method stub
		/**
		 * 判断redis是否有该key
		 */
		String tbcontent = (String) redis.opsForValue().get("tbcontent");
		if(tbcontent!=null&&!tbcontent.equals("")){
			return tbcontent;
		}
		System.out.println("没有走缓存");
		List<Map<String,Object>> list = new ArrayList<>();
		List<TbContent> tbContent = tbContentDubboServiceImpl.selectTbContent(4,false);
		for (TbContent tb : tbContent) {
        Map<String,Object> map = new HashMap<>();
			map.put("src", tb.getPic());
			map.put("widthB", 550);
			map.put("href", tb.getUrl() );
			map.put("heightB", 240);
			map.put("srcB", tb.getPic());
			map.put("height", 240);
			map.put("alt", "对不起,加载图片失败");
			map.put("width", 670);
			list.add(map);
		}
		/**
		 * 把数据缓存到redis里面
		 */
		String json = JsonUtils.objectToJson(list);
		this.redis.opsForValue().set("tbcontent", json);
		return json;
	}

}
