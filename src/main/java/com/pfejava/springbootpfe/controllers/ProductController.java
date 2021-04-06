package com.pfejava.springbootpfe.controllers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pfejava.springbootpfe.dao.Product;
import com.pfejava.springbootpfe.dao.ProductPicture;
import com.pfejava.springbootpfe.dao.ProductType;
import com.pfejava.springbootpfe.mapper_vo_bo.ProductMapper;
import com.pfejava.springbootpfe.mapper_vo_bo.ProductTypeMapper;
import com.pfejava.springbootpfe.services.ProductPictureService;
import com.pfejava.springbootpfe.services.ProductService;
import com.pfejava.springbootpfe.services.ProductTypeService;
import com.pfejava.springbootpfe.vo.ProductTypeVO;
import com.pfejava.springbootpfe.vo.ProductVO;

@RestController
@RequestMapping("/public/products")
public class ProductController {

	@Autowired()
	private ProductService productService;

	@Autowired()
	private ProductTypeService productTypeService;

	@Autowired()
	private ProductPictureService productPictureService;

	@RequestMapping(value = { "", "/" }, method = RequestMethod.GET, produces = "application/json")
	public HttpEntity<?> getProducts() {
		try {
			List<ProductVO> result = new ArrayList<ProductVO>();
			Iterator<Product> intItr = productService.findAll().iterator();
			while (intItr.hasNext()) {
				Product p = intItr.next();
				ProductVO pVO = ProductMapper.ProductBoToVo(p);
				pVO.setProductTypeVO(getProductTypeById(p.getProductTypeID()));
				pVO.setPictures(getProductPictureByProductID(p.getProductID()));
				result.add(pVO);
			}
			return new ResponseEntity<Object>(result, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public ProductTypeVO getProductTypeById(Long id) {
		Optional<ProductType> producttype = productTypeService.findById(id);
		if (producttype.isPresent()) {
			return ProductTypeMapper.ProductTypeBoToVo(producttype.get());
		}
		return null;
	}

	public List<String> getProductPictureByProductID(Long productID) {
		try {
			List<String> result = new ArrayList<String>();
			Iterator<ProductPicture> intItr = productPictureService.findByProductID(productID).iterator();
			while (intItr.hasNext()) {
				ProductPicture p = intItr.next();
				result.add(p.getProductPictureURL());
			}
			return result;
		} catch (Exception e) {
			return null;
		}
	}

//	public ProductPictureVO getProductPictureById(Long id) {
//		Optional<ProductPicture> producttype = productPictureService.findById(id);
//		if (producttype.isPresent()) {
//			return ProductPictureMapper.ProductPictureBoToVo(producttype.get());
//		}
//		return null;
//	}
}