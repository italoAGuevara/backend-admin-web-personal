package com.programadorItaloBack.app.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.programadorItaloBack.app.controller.models.entity.ReqCostumer;
import com.programadorItaloBack.app.models.dao.IReqCostumerDao;

@Service
public class ReqCostumerServiceImp implements IReqCostumerService{
	
	@Autowired
	private IReqCostumerDao costumerDao;

	//Method generals
	@Override
	public List<ReqCostumer> findAll() {		
		return (List<ReqCostumer>) costumerDao.findAll();
	}

	
	@Override
	public void delete(Long id) {
		costumerDao.deleteById(id);		
	}

	@Override
	public void save(ReqCostumer costumer){
		costumerDao.save(costumer);		
	}
	@Override
	public int numBycontact() {		
		return costumerDao.numBycontact();
	}

	//Method api
	@Override
	public ReqCostumer saveApi(ReqCostumer costumer) {
		return costumerDao.save(costumer);	
	}

	@Override
	public ReqCostumer findByIdApi(Long id) {
		return costumerDao.findById(id).orElse(null);
	}


	@Override
	public List<ReqCostumer> getContacted() {
		return costumerDao.searchByContacted();
	}

}
