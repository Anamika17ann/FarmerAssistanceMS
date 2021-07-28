package com.farm.main.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.farm.main.dao.ISupplierDao;
import com.farm.main.entity.SupplierEntity;

@Service
public class SupplierService implements ISupplierService {


	@Autowired
	ISupplierDao supplierdao;

	public Optional<SupplierEntity> getSupplierById(Long supplierId) {
		// TODO Auto-generated method stub
		return supplierdao.findById(supplierId);
	}
	
	public List<SupplierEntity> getSupplierList() {
		// TODO Auto-generated method stub
		return supplierdao.findAll();
	}
	
	public SupplierEntity addSupplier(SupplierEntity supplier) {
		// TODO Auto-generated method stub
		return supplierdao.save(supplier);
	}

	public SupplierEntity updateSupplier(SupplierEntity supplier) {
		// TODO Auto-generated method stub
		Long id = supplier.getSupplierId();
		Optional<SupplierEntity> userFound = getSupplierById(id);
		SupplierEntity updatedSupplier = null;
		if (userFound.isPresent())
			updatedSupplier = supplierdao.save(supplier);
		return updatedSupplier;
	}

	public void deleteSupplier(Long supplierId) {
		// TODO Auto-generated method stub
		supplierdao.deleteById(supplierId);
	}
	
	
	
}
