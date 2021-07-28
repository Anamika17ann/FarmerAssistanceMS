package com.exam.boot.retailer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RetailerDao extends JpaRepository<Retailer, Integer>{

}
