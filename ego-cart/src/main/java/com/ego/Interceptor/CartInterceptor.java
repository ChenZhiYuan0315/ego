package com.ego.Interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.commons.utils.CookieUtils;

@Component
public class CartInterceptor  implements HandlerInterceptor{

	@Autowired
	RedisTemplate<String,Object> redis;
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//加入购物车时判断是否登入
		String token = CookieUtils.getCookieValue(request,"TT_TOKEN");
		if(token!=null&&!token.equals("")){
			String str = (String) redis.opsForValue().get(token);
			if(str!=null&&!str.equals("")){
				return true;
			}
		}
       response.sendRedirect("http://localhost:8084/user/showLogin");
		System.out.println("进入拦截器");
		return false;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		
	}
 
}
