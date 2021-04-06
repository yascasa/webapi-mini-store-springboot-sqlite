package com.pfejava.springbootpfe.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pfejava.springbootpfe.dao.ProductType;
import com.pfejava.springbootpfe.repositories.ProductTypeRepository;


@Service
public class ProductTypeService {
	
	private final ProductTypeRepository productTypeRepository;

    @Autowired(required=true) //add this
    public ProductTypeService(ProductTypeRepository productTypeRepository) {
        this.productTypeRepository = productTypeRepository;
    }
    
	public Iterable<ProductType> findAll(){
		return this.productTypeRepository.findAll();
	}
	
	public Optional<ProductType> findById(Long id){
		return this.productTypeRepository.findById(id);
	}
	
	public ProductType save(ProductType p){
		return this.productTypeRepository.save(p);
	}
	
	public boolean delete(Long id) {
		try {
			this.productTypeRepository.deleteById(id);
			return true;
		} catch(Exception e) {
			return false;
		}
	}
}
