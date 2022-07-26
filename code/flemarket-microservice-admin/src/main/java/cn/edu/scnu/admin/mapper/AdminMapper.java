package cn.edu.scnu.admin.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.fleamarket.common.pojo.Order;
import com.fleamarket.common.pojo.OrderItem;
import com.fleamarket.common.pojo.Product;
import com.fleamarket.common.vo.EasyUIResult;



public interface AdminMapper {

	public List<Order> queryByPage(@Param("start")Integer start,@Param("rows")Integer rows);
	
	Integer queryTotal();

	public void orderSendQuery(String orderId);

	public List<OrderItem> orderDetailQuery(String orderId);


}
