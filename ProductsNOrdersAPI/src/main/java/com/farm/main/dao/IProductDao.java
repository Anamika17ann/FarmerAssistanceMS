package com.farm.main.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.farm.main.entity.Product;

@Repository
public interface IProductDao extends JpaRepository<Product, Long> {

}
