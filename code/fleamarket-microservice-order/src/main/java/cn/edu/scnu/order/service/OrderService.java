package cn.edu.scnu.order.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fleamarket.common.pojo.Order;
import com.fleamarket.common.pojo.OrderItem;

import cn.edu.scnu.order.mapper.OrderMapper;

@Service
public class OrderService {

	@Autowired
	private OrderMapper orderMapper;
	
	public void orderSave(Order order) {
		// TODO Auto-generated method stub
		System.out.println("test");
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		order.setOrderId(UUID.randomUUID().toString());
		order.setOrderTime(formatter.format(new Date()));
		order.setOrderPaystate(1);
		orderMapper.orderSave(order);	
		List<OrderItem> orderItem = order.getOrderItems();
		for(OrderItem ot : orderItem)
		{
			ot.setOrderId(order.getOrderId());
			orderMapper.orderItemSave(ot);
		}
	}

	public List<Order> orderQuery(String userId) {
		// TODO Auto-generated method stub        
		List<Order> order = orderMapper.orderQuery(userId);
		for(Order od : order){
			   String id=od.getOrderId();
			  od.setOrderItems(orderMapper.getItem(id)); 
		}
		return order;
	}

	public void sh(String orderId) {
		// TODO Auto-generated method stub
		orderMapper.ordersh(orderId);
	}

	public void delete(String orderId) {
		// TODO Auto-generated method stub
		orderMapper.orderdelete(orderId);
	}

}
