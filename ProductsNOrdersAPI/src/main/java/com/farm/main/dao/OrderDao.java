package com.farm.main.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.farm.main.entity.Orders;

@Repository
public interface OrderDao extends JpaRepository<Orders, Long> {
}
