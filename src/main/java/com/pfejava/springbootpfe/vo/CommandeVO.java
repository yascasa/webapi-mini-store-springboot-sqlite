package com.pfejava.springbootpfe.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommandeVO {
    private Long id;
    private String date;
    private String state;
}

