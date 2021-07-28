package com.farm.main.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.farm.main.entity.SupplierEntity;

@Repository
public interface ISupplierDao extends JpaRepository<SupplierEntity,Long>{

	
}
