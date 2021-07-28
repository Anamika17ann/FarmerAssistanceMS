package com.farm.main.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.farm.main.entity.Retailer;


@Repository
public interface IRetailerDao extends JpaRepository<Retailer, Integer>{

}
