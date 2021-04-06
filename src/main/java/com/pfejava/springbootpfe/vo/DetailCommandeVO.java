package com.pfejava.springbootpfe.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetailCommandeVO {
	private Long id;
	private float qte;
	private String state;
	private float price;
}


