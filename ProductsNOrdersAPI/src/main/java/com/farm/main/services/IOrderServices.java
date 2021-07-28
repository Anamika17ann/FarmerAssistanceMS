package com.farm.main.services;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.farm.main.entity.Orders;
import com.farm.main.exception.MyFarmingException;

@Service
public interface IOrderServices {

	public List<Orders> getAllOrders() throws MyFarmingException;

	public Optional<Orders> getOrderById(Long orderId) throws MyFarmingException;
	
	/*
	 * public List<Orders> getOrderByBuyerId(Long buyerId) throws
	 * MyFarmingException;
	 * 
	 * public List<Orders> getOrderBySellerId(Long sellerId) throws
	 * MyFarmingException;
	 */
	  public Optional<Orders> addOrder(Orders orders,Long postId) throws
	  MyFarmingException;
	  
	 	public Orders updateOrder(Orders orders,Long orderId )throws MyFarmingException;
	
	/*
	 * public Long getIdbyBuyerId(Long buyerId,Long orderId) throws
	 * MyFarmingException;
	 * 
	 * public Long getIdbySellerId(Long sellerId,Long orderId) throws
	 * MyFarmingException;
	 */
	public void deleteOrder(Long orderId) throws MyFarmingException;
}
