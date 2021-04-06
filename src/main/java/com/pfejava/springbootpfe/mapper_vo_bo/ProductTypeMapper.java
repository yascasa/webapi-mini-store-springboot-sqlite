package com.pfejava.springbootpfe.mapper_vo_bo;

import com.pfejava.springbootpfe.dao.ProductType;
import com.pfejava.springbootpfe.vo.ProductTypeVO;

public class ProductTypeMapper {
	
	public static ProductType ProductTypeVoToBo(ProductTypeVO pt){
		try {			
			ProductType bo = new ProductType();
			bo.setProductTypeID(pt.getId());
			bo.setProductTypeName(pt.getName());
			bo.setProductTypeDescription(pt.getDescription());
			return  bo;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}
	
	public static ProductTypeVO ProductTypeBoToVo(ProductType pt){
		try {
			ProductTypeVO vo = new ProductTypeVO();
			vo.setId(pt.getProductTypeID());
			vo.setName(pt.getProductTypeName());
			vo.setDescription(pt.getProductTypeDescription());
			return  vo;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}

}
