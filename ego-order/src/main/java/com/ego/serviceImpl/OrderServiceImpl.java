package com.ego.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.commons.pojo.EgoStatus;
import com.commons.pojo.TbItemChild;
import com.commons.utils.CookieUtils;
import com.commons.utils.JsonUtils;
import com.ego.pojo.MyOrder;
import com.ego.pojo.TbItem;
import com.ego.pojo.TbOrderItem;
import com.ego.pojo.TbUser;
import com.ego.service.ItemDubboService;
import com.ego.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {
	@Autowired
	private RedisTemplate<String, Object> redis;

	@Reference
	private ItemDubboService itemDubboServiceImpl;

	/**
	 * 把购物车的信息显示在订单系统里
	 * 
	 * @param ids
	 * @return
	 */
	@Override
	public List<TbItemChild> show(List<String> ids, HttpServletRequest request) {
		// 从redis取出购物车的信息
		String token = CookieUtils.getCookieValue(request, "TT_TOKEN");
		String json = (String) redis.opsForValue().get(token);
		TbUser tbUser = JsonUtils.jsonToPojo(json, TbUser.class);
		String key = "cart:" + tbUser.getUsername();
		String str = (String) redis.opsForValue().get(key);

		List<TbItemChild> list = new ArrayList<>();
		List<TbItemChild> tbItemChilds = JsonUtils.jsonToList(str, TbItemChild.class);
		for (TbItemChild tbItemChild : tbItemChilds) {
			for (String id : ids) {
				
				// 判断是否有货
				TbItem tbItem = itemDubboServiceImpl.showAll(Long.parseLong(id));
				if (tbItemChild.getNum() < tbItem.getNum()) {
					System.out.println(tbItemChild.getNum());
					System.out.println(tbItem.getNum());
					tbItemChild.setEnough(true);
				}else{
					tbItemChild.setEnough(false);
				}

				if (tbItemChild.getId() == Long.parseLong(id)) {
					list.add(tbItemChild);
				}

			}

		}
		return list;
	}
	/**
	 * 提交订单
	 */
	@Override
	public int OrderCreate(MyOrder myOrder, HttpServletRequest request) {
		// 从redis取出购物车的信息
		String token = CookieUtils.getCookieValue(request, "TT_TOKEN");
		String json = (String) redis.opsForValue().get(token);
		TbUser tbUser = JsonUtils.jsonToPojo(json, TbUser.class);
		String key = "cart:" + tbUser.getUsername();
		String str = (String) redis.opsForValue().get(key);
		
		List<TbItemChild> childs=new ArrayList<>();
		
		//在redis中删除已提交订单的商品
		List<TbItemChild> tbItemChilds = JsonUtils.jsonToList(str, TbItemChild.class);
		List<TbOrderItem> tbOrderItems = myOrder.getOrderItems();
		for (TbItemChild tbItemChild : tbItemChilds) {
			for (TbOrderItem tbOrderItem : tbOrderItems) {
				if(tbItemChild.getId()==Long.parseLong(tbOrderItem.getItemId())){
					childs.add(tbItemChild);
				}
			}
		}
		
		for (TbItemChild child : childs) {
			tbItemChilds.remove(child);
		}
		
		redis.opsForValue().set(key, JsonUtils.objectToJson(tbItemChilds));
		
		
		return 1;
	}

}
