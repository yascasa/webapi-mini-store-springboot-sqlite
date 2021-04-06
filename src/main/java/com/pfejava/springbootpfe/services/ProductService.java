package com.pfejava.springbootpfe.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pfejava.springbootpfe.dao.Product;
import com.pfejava.springbootpfe.repositories.ProductRepository;


@Service
public class ProductService {
	
	private final ProductRepository productRepository;

    @Autowired(required=true) //add this
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    
	public Iterable<Product> findAll(){
		return this.productRepository.findAll();
	}
	
	public Optional<Product> findById(Long id){
		return this.productRepository.findById(id);
	}
	
	public Product save(Product p){
		return this.productRepository.save(p);
	}
	
	public boolean delete(Long id) {
		try {
			this.productRepository.deleteById(id);
			return true;
		} catch(Exception e) {
			return false;
		}
	}
}
