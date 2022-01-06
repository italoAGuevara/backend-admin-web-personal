package com.programadorItaloBack.app.models.services;

import java.util.List;

import com.programadorItaloBack.app.controller.models.entity.Costumer;

public interface ICostumerService {

	public List<Costumer> findAll();
	
	public Costumer FinById(Long id);
	
	public void save(Costumer costumer);
	
	public void delete(Long id);
}
