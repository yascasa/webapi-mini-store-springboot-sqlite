package com.pfejava.springbootpfe.mapper_vo_bo;

import com.pfejava.springbootpfe.dao.Product;
import com.pfejava.springbootpfe.vo.ProductVO;

public class ProductMapper {
	
	public static Product ProductVoToBo(ProductVO productVO){
		try {			
			Product bo = new Product();
			bo.setProductID(productVO.getId());
			bo.setProductMarque(productVO.getMarque());
			bo.setProductPrice(productVO.getPrice());
			bo.setProductTypeID(productVO.getProductTypeVO().getId());
			bo.setProductName(productVO.getName());
			return  bo;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}
	
	public static ProductVO ProductBoToVo(Product productBO){
		try {
			ProductVO vo = new ProductVO();
			vo.setId(productBO.getProductID());
			vo.setMarque(productBO.getProductName());
			vo.setName(productBO.getProductName());
			vo.setPrice(productBO.getProductPrice());
			return  vo;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}

}
