package com.pfejava.springbootpfe.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pfejava.springbootpfe.dao.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
}
