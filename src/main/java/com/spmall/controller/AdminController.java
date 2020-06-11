package com.spmall.controller;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.spmall.service.AdminService;
import com.spmall.vo.CategoryVO;
import com.spmall.vo.GoodsVO;
import com.spmall.vo.GoodsViewVO;

import net.sf.json.JSONArray;

@Controller
@RequestMapping("/admin/*")
public class AdminController {
	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
	
	@Inject
	AdminService adminService;
	
	//관리자 화면
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public void getIndex() throws Exception{
		logger.info("get Index");
	}
	
	//상품 등록 페이지호출
	@RequestMapping(value = "/goods/register", method = RequestMethod.GET)
	public void getGoodsRegister(Model model) throws Exception{
		logger.info("get Goods Register");
		
		List<CategoryVO> category = null;
		category = adminService.category(); //카테고리 level 포함 호출
		model.addAttribute("category", JSONArray.fromObject(category)); //JSONArray로 category를 json타입으로 변경 후 모델명 추가
		
	}
	
	//상품등록
	@RequestMapping(value = "/goods/register", method = RequestMethod.POST)
	public String postGoodsRegister(GoodsVO vo) throws Exception{
		adminService.register(vo);
		
		return "redirect:/admin/index";//관리자화면 처음으로 
	}
	
	//상품목록확인
	@RequestMapping(value="/goods/list", method = RequestMethod.GET)
	public void getGoodsList(Model model) throws Exception{
		logger.info("get goods list");		
		
		List<GoodsVO> list = adminService.goodslist();
		model.addAttribute("list", list);
	}
	
	//상품조회 
	@RequestMapping(value="/goods/view", method=RequestMethod.GET)
	public void getGoodsview(@RequestParam("n") int gdsNum, Model model) throws Exception{
		logger.info("get goods view");
		
		GoodsViewVO goods =  adminService.goodsView(gdsNum);
		model.addAttribute("goods", goods);
	}
	
	//상품수정 get
	@RequestMapping(value="/goods/modify", method = RequestMethod.GET)
	public void getGoodsModify(@RequestParam("n") int gdsNum, Model model)throws Exception{
		logger.info("get goods modify");
		
		GoodsViewVO goods = adminService.goodsView(gdsNum);
		model.addAttribute("goods", goods);
		
		List<CategoryVO> category = null;
		category = adminService.category();
		model.addAttribute("category", JSONArray.fromObject(category)); //List를 Array로 변환
	}
	
	//상품수정 post
	@RequestMapping(value="/goods/modify", method = RequestMethod.POST)
	public String postGoodsModify(GoodsVO vo) throws Exception{
		logger.info("post goods modify");
		
		adminService.goodsModify(vo);
		return "redirect:/admin/index";
	}
	
	//상품삭제
	@RequestMapping(value="/goods/delete", method =RequestMethod.POST)
	public String goodsDelete(@RequestParam("n") int gdsNum) throws Exception{
		logger.info("post goods delete");
		adminService.goodsDelete(gdsNum);
		
		return "redirect:/admin/index";
	}
}
