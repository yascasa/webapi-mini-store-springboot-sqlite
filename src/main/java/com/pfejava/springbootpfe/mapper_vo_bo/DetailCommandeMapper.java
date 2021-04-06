package com.pfejava.springbootpfe.mapper_vo_bo;

import com.pfejava.springbootpfe.dao.DetailCommande;
import com.pfejava.springbootpfe.vo.DetailCommandeVO;

public class DetailCommandeMapper {
	
	public static DetailCommande UserVoToBo(DetailCommandeVO cmd){
		try {			
			DetailCommande bo = new DetailCommande();
			bo.setCommandeID(cmd.getId());
			bo.setDetailCommandePrice(cmd.getPrice());
			bo.setDetailCommandeQTE(cmd.getQte());
			bo.setDetailcommandeState(cmd.getState());
			return  bo;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}
	
	public static DetailCommandeVO UserBoToVo(DetailCommande cmd){
		try {
			DetailCommandeVO vo = new DetailCommandeVO();
			vo.setId(cmd.getCommandeID());
			vo.setPrice(cmd.getDetailCommandePrice());
			vo.setQte(cmd.getDetailCommandeQTE());
			vo.setState(cmd.getDetailcommandeState());
			return  vo;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}

}
