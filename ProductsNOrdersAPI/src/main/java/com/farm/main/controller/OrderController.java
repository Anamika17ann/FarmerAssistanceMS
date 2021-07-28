package com.farm.main.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.farm.main.entity.Orders;
import com.farm.main.exception.MyFarmingException;
import com.farm.main.services.IOrderServices;
import com.farm.main.services.IProductService;

@RestController
@RequestMapping("/orders")
public class OrderController {
	@Autowired
	IOrderServices iorderservices;

	@Autowired
	IProductService iproductServices;

	@GetMapping("/orderList")
	public List<Orders> getAllOrders() throws MyFarmingException {
		List<Orders> orderList = new ArrayList<Orders>();
		orderList = iorderservices.getAllOrders();

		return orderList;
	}

	@GetMapping("/{orderId}")
	public Optional<Orders> getOrderById(@PathVariable("orderId") Long orderId) throws MyFarmingException {
		Optional<Orders> order = iorderservices.getOrderById(orderId);
		return order;
	}

	@PostMapping("/product/{productId}/orders")
	public Optional<Orders> insertOrder(@PathVariable(value = "productId") Long productId,
			@Valid @RequestBody Orders orders) throws MyFarmingException {
		return iorderservices.addOrder(orders, productId);
	}

	@DeleteMapping("/{orderId}")
	public ResponseEntity<String> deleteOrder(@PathVariable Long orderId) {
		try {
			Optional<Orders> delUser = iorderservices.getOrderById(orderId);
			if (delUser.isPresent())
				iorderservices.deleteOrder(orderId);
			return new ResponseEntity<>("Record Deleted...with id : " + orderId, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Record not found with id : " + orderId, HttpStatus.EXPECTATION_FAILED);
		}
	}

	@PutMapping("/{orderId}")
	public Orders updateOrder(@RequestBody Orders orders, @PathVariable(value = "orderId") Long orderId) {
		try {
		//	System.out.println("productId:" + productId + "  orderId::" + orderId);
			Optional<Orders> userFound = iorderservices.getOrderById(orderId);
			Orders response = null;

			if (userFound.isPresent()) {
				try {
				//	System.out.println("inside condition productId:" + productId + "  orderId::" + orderId);
					response = iorderservices.updateOrder(orders, orderId);
					//System.out.println("after updaion productId:" + productId + "  orderId::" + orderId);
				} catch (Exception e) {
					e.printStackTrace();
				}

				return response;
			} else {
				return response;
			}
		} catch (Exception e) {
			return orders;
		}

	}

}
