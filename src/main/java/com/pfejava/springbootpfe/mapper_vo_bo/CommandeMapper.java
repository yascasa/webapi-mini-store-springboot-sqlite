package com.pfejava.springbootpfe.mapper_vo_bo;

import com.pfejava.springbootpfe.dao.Commande;
import com.pfejava.springbootpfe.vo.CommandeVO;

public class CommandeMapper {
	
	public static Commande UserVoToBo(CommandeVO cmd){
		try {			
			Commande bo = new Commande();
			bo.setUserID(cmd.getId());
			bo.setCommandeDate(cmd.getDate());
			bo.setCommandeState(cmd.getState());
			return  bo;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}
	
	public static CommandeVO UserBoToVo(Commande cmd){
		try {
			CommandeVO vo = new CommandeVO();
			vo.setId(cmd.getUserID());
			vo.setDate(cmd.getCommandeDate());
			vo.setState(cmd.getCommandeState());;
			return  vo;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}

}
