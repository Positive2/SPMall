package com.spmall.service;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.spmall.dao.MemberDAO;
import com.spmall.vo.MemberVO;

@Service
public class MemberServiceImpl implements MemberService {

	@Inject
	MemberDAO memberDao;
	
	//회원가입
	@Override
	public void signup(MemberVO vo) throws Exception {
		memberDao.signup(vo);
	}
	//로그인
	@Override
	public MemberVO signin(MemberVO vo) throws Exception {
		return memberDao.signin(vo);
	}
	//로그아웃
	@Override
	public void signout(HttpSession session) throws Exception {
		session.invalidate(); //세션 초기화
	}
	//아이디 중복확인
	@Override
	public int checkDupId(String userId) throws Exception {
		return memberDao.checkDupId(userId);
	}
	

}
