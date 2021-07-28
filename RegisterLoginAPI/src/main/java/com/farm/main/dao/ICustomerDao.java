package com.farm.main.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.farm.main.entity.Customer;

@Repository
public interface ICustomerDao extends JpaRepository<Customer,Long> {

	public Customer findByUserName(String userName);
	
	 @Query("SELECT r.emailId FROM Customer r ") 
	  public List<String> findEmailIdList();

}
