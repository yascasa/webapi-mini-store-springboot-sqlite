package com.pfejava.springbootpfe.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pfejava.springbootpfe.dao.ProductPicture;
import com.pfejava.springbootpfe.repositories.ProductPictureRepository;


@Service
public class ProductPictureService {
	
	private final ProductPictureRepository productPictureRepository;

    @Autowired(required=true) //add this
    public ProductPictureService(ProductPictureRepository productPictureRepository) {
        this.productPictureRepository = productPictureRepository;
    }
    
	public Iterable<ProductPicture> findAll(){
		return this.productPictureRepository.findAll();
	}
	
	public Iterable<ProductPicture> findByProductID(Long productID){
		return this.productPictureRepository.findByProductID(productID);
	}
	
	public Optional<ProductPicture> findById(Long id){
		return this.productPictureRepository.findById(id);
	}
	
	public ProductPicture save(ProductPicture p){
		return this.productPictureRepository.save(p);
	}
	
	public boolean delete(Long id) {
		try {
			this.productPictureRepository.deleteById(id);
			return true;
		} catch(Exception e) {
			return false;
		}
	}
}
