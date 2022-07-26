package cn.edu.scnu.order.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fleamarket.common.pojo.Cart;
import com.fleamarket.common.pojo.Order;
import com.fleamarket.common.vo.SysResult;

import cn.edu.scnu.order.service.OrderService;


@RestController
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@RequestMapping("/order/manage/save")
	public SysResult orderSave(Order order)
	{
		try {
			 orderService.orderSave(order);
			 return SysResult.ok();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return SysResult.build(201, e.getMessage(), null);
		}
	}
	
	@RequestMapping("/order/manage/query/{userId}")
	public SysResult orderQuery(@PathVariable String userId){
        List<Order> order=orderService.orderQuery(userId);
		return SysResult.build(200, null, order);
	}
	@RequestMapping("/order/manage/sh/{orderId}")
	public SysResult ordersh(@PathVariable String orderId){
		try {
			 orderService.sh(orderId);
			 return SysResult.ok();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return SysResult.build(201, e.getMessage(), null);
		}
	}
	@RequestMapping("/order/manage/delete/{orderId}")
	public SysResult orderdelete(@PathVariable String orderId){
		try {
			 orderService.delete(orderId);
			 return SysResult.ok();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return SysResult.build(201, e.getMessage(), null);
		}
	}

}
