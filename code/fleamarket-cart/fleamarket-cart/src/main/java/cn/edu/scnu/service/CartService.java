package cn.edu.scnu.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fleamarket.common.pojo.Cart;
import com.fleamarket.common.pojo.User;
import com.fleamarket.common.utils.MapperUtil;

import cn.edu.scnu.mapper.CartMapper;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

@Service
public class CartService {

	@Resource
	private CartMapper cartMapper;
	
//	@Resource
//	private ShardedJedisPool pool;
//	private ObjectMapper mapper = MapperUtil.MP;
	
//	public String getUserId(String ticket){
//		ShardedJedis jedis = pool.getResource();
//		try {
//			String userJson = jedis.get(ticket);
//			System.out.println(ticket);
//			System.out.println(userJson);
//			User user=mapper.readValue(userJson, User.class);
//			String newuserId=user.getUserId();
//			System.out.println(newuserId);
//			return newuserId;
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		finally {
//			jedis.close();
//			return null;
//		}
//	}
	
	public Integer cartdelete(String productId, String userId) {
		return cartMapper.cartdelete(productId,userId);
	}
	
	public List<Cart> getbyid(String proid, String userId) {
		return cartMapper.getbyid(proid,userId);
}


	public Integer cartUpdate(String productId, String userId, Integer num) {
		return cartMapper.cartUpdate(productId,userId,num);
	}

	public List<Cart> cartquery(String userId) {
		return cartMapper.cartquery(userId);
	}

	public Integer addcart(String userId, String productId, Integer num) {
		String count=cartMapper.querynumByupid(userId,productId);
		if(count==null){
			return cartMapper.addcart(userId, productId, num);
		}
		int t=Integer.parseInt(count);
		return cartMapper.cartUpdate(productId, userId, num+t);
	}

}
