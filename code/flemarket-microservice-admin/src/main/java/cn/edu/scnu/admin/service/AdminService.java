package cn.edu.scnu.admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fleamarket.common.pojo.Order;
import com.fleamarket.common.pojo.OrderItem;
import com.fleamarket.common.pojo.Product;
import com.fleamarket.common.vo.EasyUIResult;
import com.fleamarket.common.vo.SysResult;

import cn.edu.scnu.admin.mapper.AdminMapper;

@Service
public class AdminService {
	@Autowired
	private AdminMapper adminMapper;
	public EasyUIResult orderPageQuery(Integer page, Integer rows) {
		// TODO Auto-generated method stub
				// 封装数据到EasyUIResult对象
				// 1.创建一个返回的对象,将查询数据set进去然后返回
				EasyUIResult result = new EasyUIResult();
				// 2封装第一个属性 total 的数量
//				System.out.println("zxc");
				Integer total = adminMapper.queryTotal();
				System.out.println("total:"+total);
				// 3封装第二个属性List<Product> pList
				// 根据页数计算起始位置
				Integer start = (page - 1)  * rows;
				List<Order> oList = adminMapper.queryByPage(start,rows);
				// 4封装对象属性
				
				result.setTotal(total);
				result.setRows(oList);
				return result;
	}
	public void orderSendQuery(String orderId) {
		adminMapper.orderSendQuery(orderId);
	}
	
	public List<OrderItem> orderDetailQuery(String orderId) {
		List<OrderItem> list = adminMapper.orderDetailQuery(orderId);
		return adminMapper.orderDetailQuery(orderId);
		
	}

}
