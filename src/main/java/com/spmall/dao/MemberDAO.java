package com.spmall.dao;

import com.spmall.vo.MemberVO;

public interface MemberDAO {
	//회원가입
	public void signup(MemberVO vo) throws Exception;
	//로그인
	public MemberVO signin(MemberVO vo) throws Exception;
	//중복체크
	public int checkDupId(String userId) throws Exception;
	
}
