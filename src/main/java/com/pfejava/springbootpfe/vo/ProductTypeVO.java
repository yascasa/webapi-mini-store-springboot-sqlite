package com.pfejava.springbootpfe.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductTypeVO {
	private Long id;
	private String name;
	private String description;
}
