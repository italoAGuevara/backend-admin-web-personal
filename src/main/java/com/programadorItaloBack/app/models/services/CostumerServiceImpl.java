package com.programadorItaloBack.app.models.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.programadorItaloBack.app.controller.models.entity.Costumer;
import com.programadorItaloBack.app.models.dao.ICostumerDao;

@Service
public class CostumerServiceImpl implements ICostumerService{

	@Autowired
	private ICostumerDao costumerDao;
	
	@Override
	public List<Costumer> findAll() {
		return (List<Costumer>) costumerDao.findAll();
	}

	@Override
	public Costumer FinById(Long id) {
		return costumerDao.findById(id).orElse(null);
	}

	@Override
	public void save(Costumer costumer) {
		costumerDao.save(costumer);		
	}

	@Override
	public void delete(Long id) {
		costumerDao.deleteById(id);
		
	}

}
