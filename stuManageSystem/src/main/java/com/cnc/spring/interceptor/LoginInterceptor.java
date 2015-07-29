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
 * �ڿͻ��˽���url�����ʱ���Ƿ���Ȩ�޽�������
 * ��ȻҪ����ҪȨ�޿��Ƶ�url����ָ��@Login��ע��
 * json����ͨurl����ֿ�����ֻ��Ϊ�˲����´�����дjson�ķ���
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
	            //����json��ʽ����ʾ
	            pw.println("{\"result\":false,\"code\":11,\"errorMessage\":\"��δ��¼,���ȵ�¼\"}");
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
