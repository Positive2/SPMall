package com.spmall.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spmall.service.MemberService;
import com.spmall.vo.MemberVO;

@Controller
@RequestMapping("/member/*")
public class MemberController {
	//slf4j 
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	@Inject
	MemberService service;
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	
	//회원가입 get
	@RequestMapping(value="/signup", method=RequestMethod.GET) //get방식으로 호출시 
	public void getSignup() throws Exception{
		logger.info("get Signup");
	}
	
	//회원가입 post
	@RequestMapping(value="/signup", method= RequestMethod.POST )
	public String postSignup(MemberVO vo)throws Exception{
		logger.info("post Signup");
		
		String inputPass = vo.getUserPwd();
		String pass = passwordEncoder.encode(inputPass); //암호화 
		vo.setUserPwd(pass);
		
		service.signup(vo);
		
		return "redirect:/";
	}
	
	//ID 중복체크
	@RequestMapping(value="/checkDupId", method = RequestMethod.GET )
	public  @ResponseBody String checkDupId(@RequestParam String userId) throws Exception{
		logger.info("get checkDupId");
		
		int result = -1;
		String checkId = "";
		
		result =service.checkDupId(userId); //id중복확인 중복값이면 1 아니면 0
		if(result == 0) {
			checkId = userId; //중복이아니면 입력한 아이디 반환
		}
		//중복이면 ""반환
		return checkId;
	}
	
	//로그인get
	@RequestMapping(value="/signin", method= RequestMethod.GET)
	public void getSignin() throws Exception{
		logger.info("get Signin");
	}
	
	//로그인post
	@RequestMapping(value="/signin", method= RequestMethod.POST)
	public String postSignin(MemberVO vo, HttpServletRequest req, RedirectAttributes rttr) throws Exception{
		logger.info("post Signin");
		
		MemberVO login = service.signin(vo);
		HttpSession session = req.getSession();
		
		//로그인시 입력한 pw와 db에 저장된 pw 비교
		boolean pwdMatch = passwordEncoder.matches(vo.getUserPwd(), login.getUserPwd()); //비밀번호 비교
		//만약 해당 아이디가 있고 비밀번호가 일치시 
		if(login != null && pwdMatch) {
			session.setAttribute("member", login);
		} else { 
			session.setAttribute("member", null); //세션값제거
			rttr.addFlashAttribute("msg", false); //특정페이지로 이동될때 msg값 false
			return "redirect:/member/signin";	//특정페이지
		}
		return "redirect:/";
	}
	
	//로그아웃
	@RequestMapping(value ="/signout", method = RequestMethod.GET)
	public String signout(HttpSession session) throws Exception{
		logger.info("get logout");
		service.signout(session);
		return "redirect:/";
	}
}
