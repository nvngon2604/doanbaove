package J6Store.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import J6Store.dao.OrderDAO;
import J6Store.dao.OrderDetailDAO;
import J6Store.entity.Order;
import J6Store.entity.OrderDetail;
import J6Store.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	OrderDAO dao;
	
	@Autowired
	OrderDetailDAO ddao;

	@Override
	public Order create(JsonNode orderData) {
		ObjectMapper mapper = new ObjectMapper();
		
		Order order = mapper.convertValue(orderData, Order.class);
		dao.save(order);
		
		TypeReference<List<OrderDetail>> type = new TypeReference<List<OrderDetail>>() {};
		List<OrderDetail> details = mapper.convertValue(orderData.get("orderDetails"), type)
					.stream().peek(d -> d.setOrder(order)).collect(Collectors.toList());
		ddao.saveAll(details);
		
		return order;
	}

	@Override
	public Order findById(Long id) {
		return dao.findById(id).get();
	}

	@Override
	public List<Order> findByUsername(String username) {
		
		return dao.findByUsername(username);
	}

	@Override
	public List<Order> findAll() {
		return dao.findAll();
	}

	@Override
	public void delete(Long id) {
		dao.deleteById(id);
		
	}

	@Override
	public void deleteById(Long id) {
		dao.deleteById(id);
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return dao.count();
	}
	
	
	
	
}
