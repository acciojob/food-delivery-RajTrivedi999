package com.driver.ui.controller;

import java.util.ArrayList;
import java.util.List;

import com.driver.model.request.OrderDetailsRequestModel;
import com.driver.model.response.OperationStatusModel;
import com.driver.model.response.OrderDetailsResponse;
import com.driver.model.response.RequestOperationName;
import com.driver.model.response.RequestOperationStatus;
import com.driver.service.impl.OrderServiceImpl;
import com.driver.shared.dto.OrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {

	@Autowired
	OrderServiceImpl orderService;

	@GetMapping(path="/{id}")
	public OrderDetailsResponse getOrder(@PathVariable String id) throws Exception{
		OrderDto orderDto=orderService.getOrderById(id);
		return OrderDetailsResponse.builder().orderId(orderDto.getOrderId()).
				status(orderDto.isStatus()).
				cost(orderDto.getCost()).
				items(orderDto.getItems()).
				userId(orderDto.getUserId()).
				build();
	}
	
	@PostMapping()
	public OrderDetailsResponse createOrder(@RequestBody OrderDetailsRequestModel order) {
		OrderDto orderDto= orderService.createOrder(OrderDto.builder().
				userId(order.getUserId()).
				items(order.getItems()).
				cost(order.getCost()).
				build());

		return OrderDetailsResponse.builder().
				orderId(orderDto.getOrderId()).
				status(orderDto.isStatus()).
				cost(orderDto.getCost()).
				items(orderDto.getItems()).
				userId(orderDto.getUserId()).
				build();
	}
		
	@PutMapping(path="/{id}")
	public OrderDetailsResponse updateOrder(@PathVariable String id, @RequestBody OrderDetailsRequestModel order) throws Exception{
		OrderDto orderDto= orderService.updateOrderDetails(id,OrderDto.builder().
				userId(order.getUserId()).
				items(order.getItems()).
				cost(order.getCost()).
				build());

		return OrderDetailsResponse.builder().
				orderId(orderDto.getOrderId()).
				status(orderDto.isStatus()).
				cost(orderDto.getCost()).
				items(orderDto.getItems()).
				userId(orderDto.getUserId()).
				build();
	}
	
	@DeleteMapping(path = "/{id}")
	public OperationStatusModel deleteOrder(@PathVariable String id) throws Exception {
		orderService.deleteOrder(id);
		return OperationStatusModel.builder().
				operationName(String.valueOf(RequestOperationName.DELETE)).
				operationResult(String.valueOf(RequestOperationStatus.SUCCESS)).
				build();
	}
	
	@GetMapping()
	public List<OrderDetailsResponse> getOrders() {
		List<OrderDetailsResponse> orderDetailsResponses=new ArrayList<>();
		List<OrderDto> orderDtos=orderService.getOrders();
		for(OrderDto orderDto : orderDtos){
			orderDetailsResponses.add(OrderDetailsResponse.builder().
					userId(orderDto.getUserId()).
					orderId(orderDto.getOrderId()).
					cost(orderDto.getCost()).
					status(orderDto.isStatus()).
					items(orderDto.getItems()).
					build());
		}
		return orderDetailsResponses;
	}
}
