package com.spmall.service;

import java.util.List;

import com.spmall.vo.CategoryVO;
import com.spmall.vo.GoodsVO;
import com.spmall.vo.GoodsViewVO;

public interface AdminService {
	//카테고리
	public List<CategoryVO> category() throws Exception ;
	
	//상품등록
	public void register(GoodsVO vo) throws Exception;
	
	//상품목록확인
	public List<GoodsVO> goodslist() throws Exception;
	
	//상품조회
	public GoodsViewVO goodsView(int gdsNum) throws Exception;
	
	//상품수정
	public void goodsModify(GoodsVO vo) throws Exception;
	
	//상품삭제
	public void goodsDelete(int gdsNum) throws Exception;
}
