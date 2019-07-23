package com.ego.serviceImpl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference
import com.commons.pojo.EgoStatus;
import com.commons.utils.CookieUtils;
import com.commons.utils.IDUtils;
import com.commons.utils.JsonUtils;
import com.ego.pojo.TbUser;
import com.ego.service.TbUserDubboService;
import com.ego.service.TbUserService;

import ch.qos.logback.core.status.Status;

@Service
public class TbUserServiceImpl implements TbUserService{
	@Reference
	private TbUserDubboService tbUserDubboServiceImpl;
	
	@Autowired
	private RedisTemplate<String, Object> redis;
	/**
	 * 校验用户名和密码
	 * @param user
	 * @return
	 */
	@Override
	public EgoStatus login(TbUser user,HttpServletRequest request,HttpServletResponse response) {
		// TODO Auto-generated method stub
		TbUser tbUser = tbUserDubboServiceImpl.login(user);
		EgoStatus status=new EgoStatus();
		if(tbUser!=null){
			status.setStatus(200);
			String uuid = UUID.randomUUID().toString();
			String json = JsonUtils.objectToJson(tbUser);
			redis.opsForValue().set(uuid,json);
			CookieUtils.setCookie(request, response, "TT_TOKEN", uuid, 60*60*24*7);
		}else {
			status.setMsg("账号密码错误");
		
		}
		return status;
	}
	/**
	 * 单点登入
	 */
	@Override
	public EgoStatus getUserInfo(String token) {
		// TODO Auto-generated method stub
		String str = (String) redis.opsForValue().get(token);
		EgoStatus status=new EgoStatus();
		if(str!=null&&!str.equals("")){
			TbUser tbUser = JsonUtils.jsonToPojo(str, TbUser.class);
			tbUser.setPassword("");
			status.setStatus(200);
			status.setMsg("OK");
			status.setData(tbUser);
		
		}else{
			status.setMsg("获取失败");
		}
		return status;
		
		
	}
	
	/**
	 * 根据用户名查数据
	 */
	@Override
	public EgoStatus SelectName(String username) {
		// TODO Auto-generated method stub
		List<TbUser> list = tbUserDubboServiceImpl.SelectName(username);
		EgoStatus status=new EgoStatus();
		if(list != null && list.size()>0){
			status.setData(null);
			status.setStatus(400);
			status.setMsg("注册失败. 请校验数据后请再提交数据.");
		}else{
			status.setData("用户名可以使用");
		}
		return status;
	}
	/**
	 * 根据手机号查数据
	 */
	@Override
	public EgoStatus SelectPhone(String phone) {
		// TODO Auto-generated method stub
		List<TbUser> list = tbUserDubboServiceImpl.SelectPhone(phone);
	
		EgoStatus status=new EgoStatus();
		if(list!=null&&list.size()>0){
			status.setData(null);
			status.setStatus(400);
			status.setMsg("注册失败. 请校验数据后请再提交数据.");
		}else{
			status.setData("手机号可以使用");
		}
		return status;
	}
	/**
	 * 注册
	 */
	@Override
	public EgoStatus InsertUser(TbUser user) {
		// TODO Auto-generated method stub
		Date date=new Date();
		user.setCreated(date);
		user.setUpdated(date);
		user.setEmail(UUID.randomUUID().toString());
		int index = tbUserDubboServiceImpl.insertUser(user);
		EgoStatus status=new EgoStatus();
		if(index>0){
			status.setStatus(200);
		}
		return status;
	}
	
	/**
	 * 退出登入
	 */
	@Override
	public EgoStatus logout(String token, HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		redis.delete(token);
		CookieUtils.deleteCookie(request, response, "TT_TOKEN");
		EgoStatus status=new EgoStatus();
		status.setStatus(200);
		return status;
	}

}
