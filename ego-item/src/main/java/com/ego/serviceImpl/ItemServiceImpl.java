package com.ego.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.commons.pojo.TbItemChild;
import com.commons.utils.JsonUtils;
import com.ego.pojo.TbItem;
import com.ego.service.ItemDubboService;
import com.ego.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService {

	@Reference
	private ItemDubboService itemDubboServiceImpl;

	@Autowired
	RedisTemplate<String, Object> redis;

	/**
	 * 显示商品参数
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public TbItemChild show(long id) {
		// TODO Auto-generated method stub
		//判断redis是否包含此数据
		String str = (String) redis.opsForValue().get("tbItemChild" + id);
		if (str != null && !str.equals("")) {
			return JsonUtils.jsonToPojo(str, TbItemChild.class);
		} else {
			System.out.println("没有走redis");
			TbItem tbItem = itemDubboServiceImpl.showAll(id);
			TbItemChild tbItemChild = new TbItemChild();
			tbItemChild.setId(id);
			tbItemChild.setSellPoint(tbItem.getSellPoint());
			tbItemChild.setTitle(tbItem.getTitle());
			tbItemChild.setPrice(tbItem.getPrice());
			tbItemChild.setImages(tbItem.getImage() != null ? tbItem.getImage().toString().split(",") : new String[1]);
			
			//吧数据添加到redis
			String json = JsonUtils.objectToJson(tbItemChild);
			redis.opsForValue().set("tbItemChild" + id, json);
			return tbItemChild;
		}

	}

}
