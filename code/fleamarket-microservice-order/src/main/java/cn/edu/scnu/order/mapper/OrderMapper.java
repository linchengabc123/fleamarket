package cn.edu.scnu.order.mapper;

import java.util.List;

import com.fleamarket.common.pojo.Order;
import com.fleamarket.common.pojo.OrderItem;

public interface OrderMapper {

	void orderSave(Order order);

	void orderItemSave(OrderItem ot);

	List<Order> orderQuery(String userId);

	List<OrderItem> getItem(String id);

	void ordersh(String userId);

	void orderdelete(String orderId);

}
