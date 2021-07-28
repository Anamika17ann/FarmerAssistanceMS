package com.farm.main.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.farm.main.dao.IProductDao;
import com.farm.main.dao.OrderDao;
import com.farm.main.entity.Orders;
import com.farm.main.entity.Product;
import com.farm.main.exception.MyFarmingException;
import com.farm.main.exception.UserNotFoundException;

@Service
public class OrderServices implements IOrderServices {

	@Autowired
	OrderDao iorderDao;

	@Autowired
	IProductDao productDao;

	@Autowired
	ProductService productservice;

	@Override
	public List<Orders> getAllOrders() throws MyFarmingException {
		// TODO Auto-generated method stub
		return iorderDao.findAll();
	}

	@Override
	public Optional<Orders> getOrderById(Long orderId) throws MyFarmingException {
		// TODO Auto-generated method stub
		return iorderDao.findById(orderId);
	}

	@Override
	public Optional<Orders> addOrder(Orders orders, Long postId) throws MyFarmingException { // TODO Auto-generated
		Date date = new Date();
		Optional<Product> product = productservice.getProduct(postId);
		Long sellerId = product.get().getCustomerId();
		String productName = product.get().getProductName();
		return productservice.getProduct(postId).map(post -> {
			orders.setProduct(post);
			orders.setPostedDate(date);
			orders.setSellersId(sellerId);
			orders.setStatus("Pending");
			orders.setProductName(productName);
			Orders oders = iorderDao.save(orders);
			return oders;
		});
	}

	@Override
	public Orders updateOrder(Orders orders, Long orderId) throws MyFarmingException {
		// TODO Auto-generated method stub
		Date date = new Date();
		
		return iorderDao.findById(orderId).map(order -> {
			order.setProductWeight(orders.getProductWeight());
			order.setStatus(orders.getStatus());
			return iorderDao.save(order);
		}).orElseThrow(() -> new UserNotFoundException("orderId " + orderId + "not found"));
	}

	@Override
	public void deleteOrder(Long orderId) throws MyFarmingException {
		// TODO Auto-generated method stub
		if (iorderDao.findById(orderId).isPresent())
			iorderDao.deleteById(orderId);
	}

}
