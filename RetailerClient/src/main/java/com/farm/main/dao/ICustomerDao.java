
  package com.farm.main.dao;
  
  import org.springframework.data.jpa.repository.JpaRepository; import
  org.springframework.stereotype.Repository;

import com.farm.main.entity.Customer;

  
  @Repository 
  public interface ICustomerDao extends JpaRepository<Customer,Integer>{
  
  }
 