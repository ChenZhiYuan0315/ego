package com.ego.serviceImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.CloudSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.apache.solr.common.params.SolrParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.commons.pojo.TbItemChild;
import com.ego.pojo.TbItem;
import com.ego.pojo.TbItemCat;
import com.ego.pojo.TbItemDesc;
import com.ego.service.ItemCatDubboService;
import com.ego.service.ItemDescDubboService;
import com.ego.service.ItemDubboService;
import com.ego.service.ItemSearchService;

@Service
public class ItemServiceImpl implements ItemSearchService {

	@Reference
	private ItemDescDubboService itemDescDubboServiceImpl;
	@Reference
	private ItemDubboService itemDubboServiceImpl;
	@Reference 
	private ItemCatDubboService itemCatDubboServiceImpl; 
	
	/**
	 * 初始化solr
	 * @throws IOException 
	 * @throws SolrServerException 
	 */
	@Override
	public void init() throws SolrServerException, IOException {
		List<TbItem> tbItems = itemDubboServiceImpl.seAll((byte)1);
		CloudSolrClient client = new CloudSolrClient("192.168.137.128:2181,192.168.137.128:2182,192.168.137.128:2183");
		client.setDefaultCollection("collection1");
		/**
		 * 往solr添加数据
		 */
		for (TbItem tbItem : tbItems) {
			TbItemDesc itemDesc = itemDescDubboServiceImpl.selectdesc(tbItem.getId());
			TbItemCat tbItemCat = itemCatDubboServiceImpl.seleshow(tbItem.getCid());
			SolrInputDocument doc=new SolrInputDocument();
			doc.setField("id", tbItem.getId());
			doc.setField("item_title", tbItem.getTitle());
			doc.setField("item_sell_point", tbItem.getSellPoint());
			doc.setField("item_price", tbItem.getPrice());
		    doc.setField("item_image", tbItem.getImage());
			doc.setField("item_category_name", tbItemCat.getName());
		    doc.setField("item_desc", itemDesc.getItemDesc());
		    doc.setField("item_update", tbItem.getUpdated());
			client.add(doc);
		}
	
		
	client.commit();
	}

	 /**
	  * 实现搜索功能
	  * page 从哪页开始
	  * rows 当前页显示多少个
	  */
	@Override
	public Map<String, Object> SolrSearchAll(String query,int page, int rows) throws SolrServerException, IOException {
		// TODO Auto-generated method stub
		CloudSolrClient client = new CloudSolrClient("192.168.137.128:2181,192.168.137.128:2182,192.168.137.128:2183");
		client.setDefaultCollection("collection1");
	     SolrQuery params=new SolrQuery();
	     //设置p,条件
	     params.setQuery("item_keywords:"+query);
	     //从哪条数据开始分页
	     params.setStart(rows*(page-1));
	     //设置排序
	     params.setSort("item_update", ORDER.desc);
	     //每页多少个数据
	     params.setRows(rows);
	     //开始高亮
	     params.setHighlight(true);
	     params.addHighlightField("item_title");
	     params.setHighlightSimplePre("<span style='color:red'>");
			params.setHighlightSimplePost("</span>");
	     
		QueryResponse response = client.query(params);
		//获取高亮信息
		Map<String, Map<String, List<String>>> Highlighting = response.getHighlighting();
		List<TbItemChild> list=new ArrayList<>();
		
		//获取desc{}信息
		SolrDocumentList results = response.getResults();
		
		for (SolrDocument so : results) {
             TbItemChild child=new TbItemChild();
             
             child.setId(Long.parseLong(so.getFieldValue("id").toString()));
			 child.setPrice(Long.parseLong(so.getFieldValue("item_price").toString()));
			 child.setSellPoint(so.getFieldValue("item_sell_point").toString());
			 
			 //筛选Title是否为高亮
			 List<String> high = Highlighting.get(so.getFieldValue("id")).get("item_title");
			 if(high!=null&& high.size()>0){
				 child.setTitle(high.get(0));
				}else{
					child.setTitle(so.getFieldValue("item_title").toString());
				}
			 
			 Object image = so.getFieldValue("item_image");
			 child.setImages(image==null||image.equals("")?new String[1]:image.toString().split(","));
             list.add(child);
		}
		Map<String,Object> map=new HashMap<>();
		map.put("list", list);
		map.put("totalPages",results.getNumFound()%rows==0?results.getNumFound()/rows:results.getNumFound()/rows+1);
		return map;
	}

	 /**
	  * 跟后台同步新增
	  */
	@Override
	public int solrInsert(Map<String,Object> map, String desc) throws SolrServerException, IOException {
		// TODO Auto-generated method stub
		CloudSolrClient client = new CloudSolrClient("192.168.137.128:2181,192.168.137.128:2182,192.168.137.128:2183");
		client.setDefaultCollection("collection1");
		SolrInputDocument doc=new SolrInputDocument();
		doc.setField("id", map.get("id"));
		doc.setField("item_title", map.get("title"));
		doc.setField("item_sell_point", map.get("sellPoint"));
		doc.setField("item_price", map.get("price"));
	    doc.setField("item_image", map.get("image"));
		doc.setField("item_category_name", itemCatDubboServiceImpl.seleshow(Long.parseLong(map.get("cid").toString())).getName());
	    doc.setField("item_desc",desc);
		UpdateResponse response = client.add(doc);
	    client.commit();
		if(response.getStatus()==0){
			return 1;
		}
		return 0;
	}

}
