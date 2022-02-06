package com.poscoict.jblog.security;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.poscoict.jblog.service.BlogService;
import com.poscoict.jblog.vo.BlogVo;
import com.poscoict.jblog.vo.UserVo;

public class BlogInterceptor extends HandlerInterceptorAdapter {
	@Autowired
	private BlogService blogService;
	
	@Autowired
	private HttpSession session;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HandlerMethod handlerMethod = (HandlerMethod)handler;
		Map map = (Map) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE); 
		
		if(map.containsKey("id")) { 
			Object obj=map.get("id");
			String user_id = (String) obj; 
			
			BlogVo blogvo = blogService.Select(user_id);
			request.setAttribute("blogvo", blogvo);
				
			Auth auth = handlerMethod.getMethodAnnotation(Auth.class); 
			if(auth !=null) { 
				session = request.getSession();
				UserVo uservo = (UserVo)session.getAttribute("authUser");
				if(!uservo.getId().equals(user_id)) {
					response.sendRedirect(request.getContextPath() + "/jblog/blog" + user_id);
					return false;
				}
			}
			return true;
		}
		response.sendRedirect(request.getContextPath() + "/user/login");
		return false;
	}
}