package com.pfejava.springbootpfe.mapper_vo_bo;

import com.pfejava.springbootpfe.dao.ProductPicture;
import com.pfejava.springbootpfe.vo.ProductPictureVO;

public class ProductPictureMapper {
	
	public static ProductPicture ProductPictureVoToBo(ProductPictureVO productPictureVO){
		try {			
			ProductPicture bo = new ProductPicture();
			bo.setProductPictureID(productPictureVO.getProductPictureID());
			bo.setProductPictureURL(productPictureVO.getProductPictureURL());
			return  bo;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}
	
	public static ProductPictureVO ProductPictureBoToVo(ProductPicture productBO){
		try {
			ProductPictureVO vo = new ProductPictureVO();
			vo.setProductPictureID(productBO.getProductPictureID());
			vo.setProductPictureURL(productBO.getProductPictureURL());
			return  vo;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}

}
