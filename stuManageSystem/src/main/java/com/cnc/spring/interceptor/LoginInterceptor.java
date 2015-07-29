package com.cnc.spring.interceptor;

import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.cnc.spring.validation.Login;
import com.cnc.spring.validation.ResultTypeEnum;

/*
 * 在客户端进行url请求的时候看是否有权限进行请求
 * 当然要在需要权限控制的url上面指定@Login的注解
 * json和普通url请求分开做，只是为了测试下从这里写json的方法
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {
	private final Logger log = LoggerFactory.getLogger(LoginInterceptor.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		
		log.info("Interceptor working");
		HandlerMethod handler2 = (HandlerMethod)handler;
		Login login = handler2.getMethod().getAnnotation(Login.class);
		if(login == null)
			return true;
		HttpSession session = request.getSession();
		String user = (String)session.getAttribute("user");
		if(user == null) {
			log.info("Intercepting th URL-------No authority");
			if(login.value() == ResultTypeEnum.page) {
				request.getRequestDispatcher("/login").forward(request, response);
			} else if(login.value() == ResultTypeEnum.json) {
				response.setCharacterEncoding("utf-8");
	            response.setContentType("text/html;charset=UTF-8");
	            OutputStream out = response.getOutputStream();
	            PrintWriter pw = new PrintWriter(new OutputStreamWriter(out,"utf-8"));
	            //返回json格式的提示
	            pw.println("{\"result\":false,\"code\":11,\"errorMessage\":\"您未登录,请先登录\"}");
	            pw.flush();
	            pw.close();
			}
			return false;
		}				
		return true;
	}
	
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
//		response.sendRedirect(page);
		super.postHandle(request, response, handler, modelAndView);
	}
	
	
}
