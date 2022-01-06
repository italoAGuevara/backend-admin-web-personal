package com.programadorItaloBack.app.models.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.programadorItaloBack.app.controller.models.entity.Factura;
import com.programadorItaloBack.app.models.dao.IFacturaDao;

@Service
public class FacturaServiceImpl implements IFacturaService{

	@Autowired
	private IFacturaDao facturaDao;

	@Override
	public List<Factura> findAll() {
		return (List<Factura>) facturaDao.findAll();
	}

	@Override
	public Factura FinById(Long id) {
		return facturaDao.findById(id).orElse(null);
	}

	@Override
	public void save(Factura factura) {
		facturaDao.save(factura);		
	}

	@Override
	public void delete(Long id) {
		facturaDao.deleteById(id);		
	}

}
