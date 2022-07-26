package cn.edu.scnu.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fleamarket.common.pojo.OrderItem;
import com.fleamarket.common.vo.EasyUIResult;
import com.fleamarket.common.vo.SysResult;

import cn.edu.scnu.admin.service.AdminService;

@RestController
public class AdminController {
	@Autowired
	private AdminService adminService;
	
	@RequestMapping("/admin/manage/orderManage")
	public EasyUIResult orderPageQuery(Integer page,Integer rows){
		System.out.println("page:" + page + " rows: " + rows);
		EasyUIResult result = adminService.orderPageQuery(page,rows);
		return result;
	}
	
	@RequestMapping("/admin/manage/send")
	public SysResult orderSendQuery(String orderId){
		System.out.println("Jinlaile");
		try {
			adminService.orderSendQuery(orderId);
			return SysResult.ok();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return SysResult.build(201, e.getMessage(), null);
		}
	}
	
	@RequestMapping("/admin/manage/query/{orderId}")
	public List<OrderItem> orderDetailQuery(@PathVariable String orderId){
		System.out.println("!!!!!!!!!");
		System.out.println(orderId);
		
//		System.out.println(adminService.orderDetailQuery(orderId).getNum());
		adminService.orderDetailQuery(orderId);
		List<OrderItem> list = adminService.orderDetailQuery(orderId);
		return list;
//		try {
//			return adminService.orderDetailQuery(orderId);
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//			return null;
//		}
	}
}
