package com.spmall.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.spmall.vo.MemberVO;

public class AdminInterceptor  extends HandlerInterceptorAdapter{ 
	
	@Override
	public boolean preHandle(HttpServletRequest req,
				HttpServletResponse res, Object obj) throws Exception{
		HttpSession session = req.getSession(); //현재 세션 불러옴
		//member라는 세션 불러와 MemberVO형태로 변환
		MemberVO memberVO = (MemberVO)session.getAttribute("member"); 
		
		//member값이 없으면 로그인하지않은 상태로 로그인화면으로 이동
		if(memberVO == null) {
			res.sendRedirect("/member/signin");
			return false;
		}
		
		//  관리가 아닐경우 home
		//preHandle 반환값이 true= 컨트롤러 실행 false = 멈춤
		if( memberVO.getVerify() != 9) {
			res.sendRedirect("/");
			return false;
		}		
		return true;
	}
}
