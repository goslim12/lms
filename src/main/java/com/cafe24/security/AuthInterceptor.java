package com.cafe24.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.cafe24.lms.domain.User;
import com.cafe24.security.Auth.Role;

public class AuthInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub

		// 1.핸들러 종류 확인
		if (handler instanceof HandlerMethod == false) {
			return true;
		}

		// 2. casting
		HandlerMethod handlerMethod = (HandlerMethod) handler;

		// 3. @Auth 받아오기.
		Auth auth = handlerMethod.getMethodAnnotation(Auth.class);

		// 4. Method에 @Auth가 없는 경우
		if (auth == null) {
			return true;
		}

		// 5. @Auth 가 붙어 있는 경우, 인증 여부 체크
		HttpSession session = request.getSession();
		if (session == null) {
			response.sendRedirect(request.getContextPath() + "/user/login");
			return false;
		}

		User authUser = (User) session.getAttribute("authUser");
		if (authUser == null) {
			response.sendRedirect(request.getContextPath() + "/user/login");
			return false;
		}

		// 6. Role 가져오기
		Auth.Role role = auth.value();

		// 7. USER Role 접근이면 인증만 되어있으면 허용
		if (role == Auth.Role.USER) {
			return true;
		}

		// 8. ADMIN Role 접근
		// ADMIN 권한이 없는 사용자이면 메인으로...
		if (authUser.getRole().name().equals("ADMIN") == false) {
			response.sendRedirect(request.getContextPath());
			return false;
		}

		// 9. ADMIN Role 접근 허용
		return true;
	}

}
