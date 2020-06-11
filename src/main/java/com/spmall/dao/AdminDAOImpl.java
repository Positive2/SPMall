package com.spmall.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.spmall.vo.CategoryVO;
import com.spmall.vo.GoodsVO;
import com.spmall.vo.GoodsViewVO;

@Repository
public class AdminDAOImpl implements AdminDAO {
	@Inject
	private SqlSession sqlsession;
	
	//매퍼
	private static String namespace = "com.spmall.mappers.adminMapper";
	
	//카테고리
	@Override
	public List<CategoryVO> category() throws Exception {
		return sqlsession.selectList(namespace + ".category");
	}

	
	//상품등록
	@Override
	public void register(GoodsVO vo) throws Exception {
		sqlsession.insert(namespace + ".register", vo);
	}

	//상품목록확인
	@Override
	public List<GoodsVO> goodslist() throws Exception {
		return sqlsession.selectList(namespace + ".goodslist");
	}

	//상품조회
	@Override
	public GoodsViewVO goodsView(int gdsNum) throws Exception {
		return sqlsession.selectOne(namespace + ".goodsView", gdsNum);
	}
	
	//상품 수정
	@Override
	public void goodsModify(GoodsVO vo) throws Exception {
		sqlsession.update(namespace + ".goodsModify", vo);
	}


	@Override
	public void goodsDelete(int gdsNum) throws Exception {
		sqlsession.delete(namespace + ".goodsDelete", gdsNum);
		
	}

}
