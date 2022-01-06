package com.programadorItaloBack.app.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.programadorItaloBack.app.controller.models.entity.ReqCostumer;

public interface IReqCostumerDao extends CrudRepository<ReqCostumer, Long>{
	
	@Query(value = "SELECT * FROM db_developer_italo.reque_costumers  WHERE contacted = false",
			nativeQuery = true)
	public List<ReqCostumer> searchByContacted();
	
	@Query(value="SELECT COUNT(*) FROM db_developer_italo.reque_costumers  WHERE contacted = false",
			nativeQuery = true)
	public int numBycontact();
}
