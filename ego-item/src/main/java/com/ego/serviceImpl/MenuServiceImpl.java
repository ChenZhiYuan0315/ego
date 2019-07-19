package com.ego.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ego.pojo.Menu;
import com.ego.pojo.PortalMenu;
import com.ego.pojo.TbItemCat;
import com.ego.service.ItemCatDubboService;
import com.ego.service.MenuService;

@Service
public class MenuServiceImpl implements MenuService{

	@Reference
	private ItemCatDubboService itemCatDubboServiceImpl;
	/*
	 * 查询出所有分类类目并转换为特定类型.  
	 */
	@Override
	public Menu PortalCatMenu() {
		List<TbItemCat> show = itemCatDubboServiceImpl.show(0);
		Menu menu=new Menu();
		menu.setData(SeleAllMenu(show));
		return menu;
	}
       
	public List<Object> SeleAllMenu(List<TbItemCat> show){
		List<Object> arrayList=new ArrayList<>();
		
		for (TbItemCat list : show) {
			if(list.getIsParent()){
				PortalMenu portalMenu=new PortalMenu();
				portalMenu.setU("/products/"+list.getId()+".html");
				portalMenu.setN("<a href='/products/"+list.getId()+".html'>"+list.getName()+"</a>");
				portalMenu.setI(SeleAllMenu(itemCatDubboServiceImpl.show(list.getId())));
				arrayList.add(portalMenu);
			}else{
				arrayList.add("/products/"+list.getId()+".html|"+list.getName());
			}
			}
		return arrayList;
		
	}
}

