package com.pfejava.springbootpfe.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pfejava.springbootpfe.dao.ProductType;

@Repository
public interface ProductTypeRepository extends CrudRepository<ProductType, Long> {
}
