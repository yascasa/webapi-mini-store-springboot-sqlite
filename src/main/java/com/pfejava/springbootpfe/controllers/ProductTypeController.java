package com.pfejava.springbootpfe.controllers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pfejava.springbootpfe.dao.ProductType;
import com.pfejava.springbootpfe.mapper_vo_bo.ProductTypeMapper;
import com.pfejava.springbootpfe.services.ProductTypeService;
import com.pfejava.springbootpfe.vo.ProductTypeVO;

@RestController
@RequestMapping("/public/products")
public class ProductTypeController {

	@Autowired()
	private ProductTypeService productTypeService;

	@RequestMapping(value = "/categories", method = RequestMethod.GET, produces = "application/json")
	public HttpEntity<?> getCategories() {
		try {
			List<ProductTypeVO> result = new ArrayList<ProductTypeVO>();
			Iterator<ProductType> intItr = productTypeService.findAll().iterator();
			while (intItr.hasNext()) {
				ProductType u = intItr.next();
				result.add(ProductTypeMapper.ProductTypeBoToVo(u));
			}
			return new ResponseEntity<Object>(result, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}