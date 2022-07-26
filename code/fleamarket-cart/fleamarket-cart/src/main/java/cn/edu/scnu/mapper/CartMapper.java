package cn.edu.scnu.mapper;

import java.util.List;

import com.fleamarket.common.pojo.Cart;

public interface CartMapper {

	Integer cartdelete(String productId, String userId);

	Integer cartUpdate(String productId, String userId, Integer num);

	List<Cart> cartquery(String userId);

	Integer addcart(String userId, String productId, Integer num);

	String querynumByupid(String userId, String productId);
	List<Cart> getbyid(String proid ,String userId);
}
