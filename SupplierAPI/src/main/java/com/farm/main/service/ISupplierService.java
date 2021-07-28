package com.farm.main.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.farm.main.entity.SupplierEntity;

@Service
public interface ISupplierService {

	public Optional<SupplierEntity> getSupplierById(Long supplierId);

	public List<SupplierEntity> getSupplierList();

	public SupplierEntity addSupplier(SupplierEntity supplier);

	public SupplierEntity updateSupplier(SupplierEntity supplier);

	public void deleteSupplier(Long supplierId);

	
	
}
