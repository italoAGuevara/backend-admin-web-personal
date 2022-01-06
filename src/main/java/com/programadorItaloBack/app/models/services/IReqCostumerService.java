package com.programadorItaloBack.app.models.services;

import java.util.List;

import com.programadorItaloBack.app.controller.models.entity.ReqCostumer;

public interface IReqCostumerService {

	//methos general
	public List<ReqCostumer> findAll();
	
	public void save(ReqCostumer costumer);
	
	public void delete(Long id);
	
	public List<ReqCostumer> getContacted();
	
	public int numBycontact();
	
	//Methos api rest
	public ReqCostumer saveApi(ReqCostumer costumer);

	public ReqCostumer findByIdApi(Long id);

	

}
