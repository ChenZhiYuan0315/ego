package com.ego.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.commons.pojo.TbItemChild;
import com.ego.pojo.Menu;
import com.ego.service.ItemService;
import com.ego.service.MenuService;

@Controller
public class PortalMenuController {

	@Autowired
	private MenuService menuServiceImpl;
	@Autowired
	private ItemService itemServiceImpl;
	
   @RequestMapping("/rest/itemcat/all")
   @ResponseBody
   public MappingJacksonValue all(String callback){
	   MappingJacksonValue mapping=new MappingJacksonValue(menuServiceImpl.PortalCatMenu());
	   mapping.setJsonpFunction(callback);
	return mapping;
	   
   }
   
   /**
    * 显示商品参数
    * @param id
    * @return
    */
   @RequestMapping("item/{id}.html")
   public String item(@PathVariable long id,Model model){
	   TbItemChild show = itemServiceImpl.show(id);
	   model.addAttribute("item", show);
	return "item";
	   
   }
}
