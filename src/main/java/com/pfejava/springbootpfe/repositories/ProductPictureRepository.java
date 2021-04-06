package com.pfejava.springbootpfe.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pfejava.springbootpfe.dao.ProductPicture;

@Repository
public interface ProductPictureRepository extends CrudRepository<ProductPicture, Long> {
	public Iterable<ProductPicture> findByProductID(Long productID);
}
