package com.spmall.dao;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.spmall.vo.MemberVO;

@Repository
public class MemberDAOImpl implements MemberDAO{
	
	@Inject
	private SqlSession sqlSession;
	
	private static String namespace = "com.spmall.mappers.memberMapper";
	
	//회원가입
	@Override
	public void signup(MemberVO vo) throws Exception {
		sqlSession.insert(namespace + ".signup", vo);
	}
	
	//로그인
	@Override
	public MemberVO signin(MemberVO vo) throws Exception {
		
		return sqlSession.selectOne(namespace + ".signin", vo);
	}
	
	//아이디 중복확인
	@Override
	public int checkDupId(String userId) throws Exception {
		return sqlSession.selectOne(namespace + ".checkDupId", userId);
	}
	
}
