package com.programadorItaloBack.app.models.services;

import java.util.List;

import com.programadorItaloBack.app.controller.models.entity.Factura;

public interface IFacturaService {

	public List<Factura> findAll();
	
	public Factura FinById(Long id);
	
	public void save(Factura factura);
	
	public void delete(Long id);
}
