package com.ego.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.ego.mapper.TbUserMapper;
import com.ego.pojo.TbUser;
import com.ego.pojo.TbUserExample;
import com.ego.service.TbUserDubboService;

@Service
@Component
public class TbUserDubboServiceImpl implements TbUserDubboService{

	@Autowired
	private TbUserMapper tbUserMapper;
	/**
	 * 校验用户名和密码
	 * @param user
	 * @return
	 */
	@Override
	public TbUser login(TbUser user) {
		// TODO Auto-generated method stub
		TbUserExample example=new TbUserExample();
		example.createCriteria().andUsernameEqualTo(user.getUsername()).andPasswordEqualTo(user.getPassword());
		List<TbUser> list = tbUserMapper.selectByExample(example);
		if(list!=null&&list.size()>0){
			return list.get(0);
		}
		return null;
	}
	
	/**
	 * 注册 根据用户名查数据
	 */
	@Override
	public List<TbUser> SelectName(String username) {

		TbUserExample example=new TbUserExample();
		example.createCriteria().andUsernameEqualTo(username);
		return tbUserMapper.selectByExample(example);
	}

	/**
	 * 注册 根据手机号查数据
	 */
	@Override
	public List<TbUser> SelectPhone(String phone) {
		TbUserExample example=new TbUserExample();
		example.createCriteria().andPhoneEqualTo(phone);
		return tbUserMapper.selectByExample(example);
	}

	/**
	 * 注册 
	 */
	@Override
	public int insertUser(TbUser user) {
		// TODO Auto-generated method stub
		
		return tbUserMapper.insert(user);
	}

	
}
