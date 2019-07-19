package com.ego.serviceImpl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.config.annotation.Service;
import com.commons.pojo.EasyUIDataGrid;
import com.ego.mapper.TbItemDescMapper;
import com.ego.mapper.TbItemMapper;
import com.ego.pojo.TbItem;
import com.ego.pojo.TbItemDesc;
import com.ego.pojo.TbItemExample;
import com.ego.service.ItemDubboService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;



@Service
@Component
public class ItemDubboServiceImpl implements ItemDubboService {


	@Autowired
	private TbItemMapper tbItemMapper;
	
	@Autowired
	private TbItemDescMapper tbItemDescMapper;
	
/**
 * 查询全部并分页
 */
	@Override
	public EasyUIDataGrid show(int age, int rows) {
		// TODO Auto-generated method stub
		PageHelper.startPage(age, rows);
		List<TbItem> list = tbItemMapper.selectByExample(new TbItemExample());
		System.out.println(list);
		PageInfo<TbItem> pi = new PageInfo<>(list);
		EasyUIDataGrid easy = new EasyUIDataGrid();
		easy.setRows(pi.getList());
		easy.setTotal(pi.getTotal());
		return easy;
	}

	/**
	 * 修改商品status（状态值）值
	 * 值1表示 表示正常
	 * 值2表示 下架
	 * 值3表示 删除
	 */
	@Override
	public int updItemStaus(TbItem tbItem) {
		// TODO Auto-generated method stub
		return tbItemMapper.updateByPrimaryKeySelective(tbItem);
	}

	  /**
	   * 多表新增商品 item 和 item_desc
	 * @throws Exception 
	   */
	@Transactional(rollbackFor=Exception.class)
	@Override
	public int inserItemAndItemdesc(TbItem tbItem, TbItemDesc tbItemDesc) throws Exception {
		// TODO Auto-generated method stub
		int index=0;
	     System.out.println(tbItem);
			try {
				index = tbItemMapper.insertSelective(tbItem);
				
				index = index + tbItemDescMapper.insertSelective(tbItemDesc);
						
			} catch (Exception e) {
				// TODO Auto-generated catch block
				throw new Exception("新增失败,事务回滚");
			}
			if(index==2){
				return 1;
			}else{
				return 0;
			}
			
	}

	/**
	   * 查询全部status为1的商品
	   */
	@Override
	public List<TbItem> seAll(byte status) {
		// TODO Auto-generated method stub
		TbItemExample example=new TbItemExample();
		example.createCriteria().andStatusEqualTo(status);
		return tbItemMapper.selectByExample(example);
	}

	@Override
	public TbItem showAll(long id) {
		// TODO Auto-generated method stub
		return tbItemMapper.selectByPrimaryKey(id);
	}


}
