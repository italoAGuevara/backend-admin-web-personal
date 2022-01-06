package com.programadorItaloBack.app.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.programadorItaloBack.app.controller.models.entity.Costumer;

public interface ICostumerDao extends CrudRepository<Costumer, Long>{
	
}
