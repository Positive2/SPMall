package com.spmall.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.spmall.dao.AdminDAO;
import com.spmall.vo.CategoryVO;
import com.spmall.vo.GoodsVO;
import com.spmall.vo.GoodsViewVO;

@Service
public class AdminServiceImpl implements AdminService{

	@Inject
	private AdminDAO adminDAO;
	
	//카테고리
	@Override
	public List<CategoryVO> category() throws Exception {
		return adminDAO.category();
	}
	
	//상품등록
	@Override
	public void register(GoodsVO vo) throws Exception {
		adminDAO.register(vo);
	}

	//상품 목록확인
	@Override
	public List<GoodsVO> goodslist() throws Exception {
		return adminDAO.goodslist();
	}
	
	//상품 조회
	@Override
	public GoodsViewVO goodsView(int gdsNum) throws Exception {
		return adminDAO.goodsView(gdsNum);
	}
    
	//상품 수정
	@Override
	public void goodsModify(GoodsVO vo)throws Exception{
		adminDAO.goodsModify(vo);
	}

	//상품 삭제
	@Override
	public void goodsDelete(int gdsNum) throws Exception {
		adminDAO.goodsDelete(gdsNum);	
	}
}
