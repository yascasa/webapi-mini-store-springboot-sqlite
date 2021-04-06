package com.pfejava.springbootpfe.vo;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductVO {
	private Long id;
	private String name;
	private float price;
	private String marque;
	private ProductTypeVO productTypeVO;
	private List<String> pictures;
}
