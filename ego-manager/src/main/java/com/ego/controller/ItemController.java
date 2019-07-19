package com.ego.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.commons.pojo.EasyUIDataGrid;
import com.commons.pojo.EgoStatus;
import com.ego.pojo.TbItem;
import com.ego.service.ItemService;

@Controller
public class ItemController {
	
   @Autowired
   private ItemService itemServiceImpl;
   
	//视图转发
	@RequestMapping("{page}")
	public String DomainCombiner(@PathVariable("page") String page){
		return page;
	}
	/**
	 * 显示商品修改
	 * @return
	 */
	@RequestMapping("rest/page/item-edit")
	public String showItemEdit(){
		return "item-edit";
	}
	
     
	/**
	 * 查询全部商品并分页
	 * @param page
	 * @param rows
	 * @return 
	 */
	@RequestMapping("/item/list")
	@ResponseBody
	public EasyUIDataGrid Item(int page,int rows){
		EasyUIDataGrid show = itemServiceImpl.show(page, rows);
		return show;
	}
	
	/**
	 * 删除商品
	 */
    @RequestMapping("/rest/item/delete")
    @ResponseBody
    public EgoStatus delete(String ids){
    	EgoStatus status=new EgoStatus();
    	int updItemStaus = itemServiceImpl.updItemStaus(ids,(byte) 3);
    	if(updItemStaus==1){
    		status.setStatus(200);	
    	}
		return status;

    }
    
    /**
     * 下架商品
     * @param ids
     * @return
     */
    @RequestMapping("/rest/item/instock")
    @ResponseBody
    public EgoStatus instock(String ids){
    	EgoStatus status=new EgoStatus();
    	int updItemStaus = itemServiceImpl.updItemStaus(ids,(byte) 2);
    	if(updItemStaus==1){
    	     status.setStatus(200);
    	}
		return status;
    }
    
    /**
     * 上架商品
     * @param ids
     * @return
     */
    @RequestMapping("/rest/item/reshelf")
    @ResponseBody
    public EgoStatus reshelf(String ids){
    	EgoStatus status=new EgoStatus();
    	int updItemStaus = itemServiceImpl.updItemStaus(ids,(byte) 1);
    	if(updItemStaus==1){
    		status.setStatus(200);
    	}
		return status;
    }
    
    /**
     * 新添商品
     * @throws Exception 
     */
    @RequestMapping("/item/save")
    @ResponseBody
    public EgoStatus save(TbItem item,String desc) throws Exception{
    	EgoStatus status=new EgoStatus();
    	try {
			int inserItemItemdesc = itemServiceImpl.inserItemAndItemdesc(item, desc);
	        if(inserItemItemdesc==1){
				
				status.setStatus(200);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
		return status;
    	
    }
}
