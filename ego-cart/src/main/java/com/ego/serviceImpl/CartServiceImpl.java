package com.ego.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.commons.pojo.TbItemChild;
import com.commons.utils.CookieUtils;
import com.commons.utils.JsonUtils;
import com.ego.pojo.TbItem;
import com.ego.pojo.TbUser;
import com.ego.service.CartService;
import com.ego.service.ItemDubboService;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	private RedisTemplate<String,Object> redis;
	@Reference
	private ItemDubboService  itemDubboServiceImpl;
	
	/**
	 * 把购物车的信息存进redis中
	 */
	@Override
	public void CartRedis(long id, int num,HttpServletRequest request) {

		List<TbItemChild> list=new ArrayList<>();

		String token = CookieUtils.getCookieValue(request, "TT_TOKEN");
		String json = (String) redis.opsForValue().get(token);
		TbUser tbUser = JsonUtils.jsonToPojo(json, TbUser.class);
		
		String key ="cart:"+tbUser.getUsername();
		
		//判断购物车里面是否有这商品
		String str = (String) redis.opsForValue().get(key);
		
		if(str!=null&&!str.equals("")){
			list = JsonUtils.jsonToList(str, TbItemChild.class);
			
			for (TbItemChild tbItemChild : list) {
				if(tbItemChild.getId()==id){
					tbItemChild.setNum(tbItemChild.getNum()+num);
					redis.opsForValue().set(key, JsonUtils.objectToJson(list));
					return ;
				}
		
				
			}
		}
		  //没有该商品则添加商品
			TbItem tbItem = itemDubboServiceImpl.showAll(id);
			TbItemChild child=new TbItemChild();
			child.setTitle(tbItem.getTitle());
			child.setId(id);
			child.setNum(num);
			child.setPrice(tbItem.getPrice());
			child.setImages(tbItem.getImage()!=null?tbItem.getImage().split(","):new String[1]);
			list.add(child);

		    redis.opsForValue().set(key, JsonUtils.objectToJson(list));
		
		    return;
		}
	/**
	  * 显示购物车信息
	  */
	@Override
	public List<TbItemChild> showcart(HttpServletRequest request) {
		// TODO Auto-generated method stub
		String token = CookieUtils.getCookieValue(request, "TT_TOKEN");
		String json = (String) redis.opsForValue().get(token);
		TbUser tbUser = JsonUtils.jsonToPojo(json, TbUser.class);
		String key ="cart:"+tbUser.getUsername();
		//判断购物车里面是否有这商品
		String str = (String) redis.opsForValue().get(key);
		
		return JsonUtils.jsonToList(str, TbItemChild.class);
	}
	
	/**
	 * 商品的增加和减少
	 */
	@Override
	public int cartupdate(long id, int num,HttpServletRequest request) {
		// TODO Auto-generated method stub
		String token = CookieUtils.getCookieValue(request, "TT_TOKEN");
		String json = (String) redis.opsForValue().get(token);
		TbUser tbUser = JsonUtils.jsonToPojo(json, TbUser.class);
		String key ="cart:"+tbUser.getUsername();
		//判断购物车里面是否有这商品
		String str = (String) redis.opsForValue().get(key);
		
		List<TbItemChild> lTbItemChilds = JsonUtils.jsonToList(str, TbItemChild.class);
		for (TbItemChild tbItemChild : lTbItemChilds) {
			if(tbItemChild.getId()==id){
				tbItemChild.setNum(num);
			}
		}
		redis.opsForValue().set(key, JsonUtils.objectToJson(lTbItemChilds));

		return 0;
	}

	  /**
		 * 商品的删除
		 */
	@Override
	public int CartDelete(long id, HttpServletRequest request) {
		String token = CookieUtils.getCookieValue(request, "TT_TOKEN");
		String json = (String) redis.opsForValue().get(token);
		TbUser tbUser = JsonUtils.jsonToPojo(json, TbUser.class);
		String key ="cart:"+tbUser.getUsername();
		//判断购物车里面是否有这商品
		String str = (String) redis.opsForValue().get(key);
		List<TbItemChild> tbItemChilds = JsonUtils.jsonToList(str, TbItemChild.class);
	
		TbItemChild childs=null;
		for (TbItemChild tbItemChild : tbItemChilds) {
			if (tbItemChild.getId()==id) {
				childs=tbItemChild;
		
			}
		}
	      tbItemChilds.remove(childs);
	
		redis.opsForValue().set(key, JsonUtils.objectToJson(tbItemChilds));
		return 1;
	}
	

}
