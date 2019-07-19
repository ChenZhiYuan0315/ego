package com.ego.service;

import java.io.IOException;
import java.util.Map;

import org.apache.solr.client.solrj.SolrServerException;

import com.ego.pojo.TbItem;

public interface ItemSearchService {
	/**
	 * 为solr添加数据
	 * @throws SolrServerException
	 * @throws IOException
	 */
 void init() throws SolrServerException, IOException ;
 
 /**
  * 实现搜索功能
  * page 从哪页开始
  * rows 当前页显示多少个
  */
 Map<String, Object> SolrSearchAll(String query,int page,int rows) throws SolrServerException, IOException;
 /**
  * 跟后台同步新增
  */
 int solrInsert(Map<String, Object> map,String desc) throws SolrServerException, IOException;
}
