package com.poscoict.jblog.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.poscoict.jblog.vo.UserVo;


public class AuthInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//1.handler 종류 확인
		if(handler instanceof HandlerMethod==false) {
			return true;
		}
		
		//2.casting
		HandlerMethod handlerMethod = (HandlerMethod)handler;
		
		//3.Handler Method의 @Auth 받아오기
		Auth auth = handlerMethod.getMethodAnnotation(Auth.class);
		
		//4. Handler Method @Auth가 없다면 Type에 있는지 확인(과제)
		if(auth==null) {
			//auth = handlerMethod.getBeanType().getAnnotation(Auth.class);
			auth = handlerMethod.getMethod().getDeclaringClass().getAnnotation(Auth.class);
		}
		
		//5.type과 method에 @Auth가 적용이 안되어있는 경우
		if(auth==null){
			return true;
		}		
		
		//
		
		//6. @Auth가 적용이 되어있기 때문에 인증(Authentication) 여부 확인
		HttpSession session = request.getSession();
		if(session==null) {
			response.sendRedirect(request.getContextPath()+"/user/login");
			return false;
		}	
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		if(authUser==null) {
			response.sendRedirect(request.getContextPath()+"/user/login");
			return false;
		}
		/*
		 * if(auth.role().equals("ADMIN")) { if(authUser.getRole().equals("ADMIN")) {
		 * return true; } response.sendRedirect(request.getContextPath()); return false;
		 * }
		 */
		
		//7. 권한(Authorization)체크를 위해서 @Auth의 role 가져오기("USER","ADMIN")
		String role = auth.role();
	
		//8. @Auth의 role이 "USER"인 경우, authUser의 role은 상관이 없다
		if("USER".equals(role)) {
			return true;
		}
		
		//9. @Auth의 role이 "ADMIN"인 경우, authUser의 role은 ADMIN이어야한다.
	
		
		//10. 옳은관리자
		//@Auth의 role : "ADMIN"
		//authUser의 role : "ADMIN"
		return true;
		//7.인증 확인!! -> controller의 handler(method) 실행
	}

}
