package com.ego.serviceImpl;



import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import com.alibaba.dubbo.config.annotation.Reference;
import com.commons.pojo.EasyUIDataGrid;
import com.commons.utils.HttpClientUtil;
import com.commons.utils.IDUtils;
import com.commons.utils.JsonUtils;
import com.ego.pojo.TbItem;
import com.ego.pojo.TbItemDesc;
import com.ego.service.ItemDubboService;
import com.ego.service.ItemService;


@Service
public class ItemServiceImpl implements ItemService{
   
	//暴露服务
   @Reference(check=false)
   ItemDubboService itemDubboService;
	
   @Autowired
   RedisTemplate<String, Object> redis;
   /**
	 * 查询全部并分页
	 */
	@Override 
	public EasyUIDataGrid show(int age, int rows) {
		// TODO Auto-generated method stub
		return itemDubboService.show(age, rows);
	}

	/**
	 * 修改商品status（状态值）值
	 * 值1表示 表示正常
	 * 值2表示 下架
	 * 值3表示 删除
	 */
	@Override
	public int updItemStaus(String ids,byte status) {
		// TODO Auto-generated method stub
		TbItem tbItem=new TbItem();
		int index=0;
		   String[] split = ids.split(",");
		   for (String id : split) {
			 tbItem.setId(Long.parseLong(id));
			 tbItem.setStatus(status); 
			 int updItemStaus = itemDubboService.updItemStaus(tbItem);
			 
			 //清空redis中的数据 该id在ego-item项目中ItemServieImpl
			 redis.delete("tbItemChild"+id);
			 index++;
		}
		  if(index==split.length){
			  return 1;
		  }
		return 0;
	}

	@Override
	public int inserItemAndItemdesc(TbItem item, String desc) throws Exception {
		Date date=new Date();
	    long id=IDUtils.genItemId();
	    
		item.setId(id);
		item.setStatus((byte)1);
		item.setCreated(date);
		item.setUpdated(date);

		TbItemDesc itemDesc=new TbItemDesc();
		itemDesc.setItemDesc(desc);
		itemDesc.setItemId(id);
		itemDesc.setCreated(date);
		itemDesc.setUpdated(date);
		
		int  inserItemItemdesc= itemDubboService.inserItemAndItemdesc(item, itemDesc);
        
		/**
		  * 往solr新增数据
		  */
		 new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				 Map<String,Object> map=new HashMap<>();
			     map.put("tbItem", item);
			     map.put("desc",desc);
				 HttpClientUtil.doPostJson("http://localhost:8083/solr/add",JsonUtils.objectToJson(map));
			}
		}).start();;
	    
		 if(inserItemItemdesc==1){
			return 1;
		}
		
		return 0;
	
	}  
 
}
