package com.pfejava.springbootpfe.dao;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


//LOMBOKS
@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity // This tells Hibernate to make a table out of this class
@Table(name = "Product")
public class Product {

	// ATTRIBUTES
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long productID;
	private String productName;
	

	private String productmfDate;
	private String productexpDate;
	
	private float productPrice;
	private String productMarque;
	private Long productTypeID;

}