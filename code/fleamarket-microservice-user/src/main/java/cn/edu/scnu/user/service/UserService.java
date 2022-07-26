package cn.edu.scnu.user.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fleamarket.common.pojo.User;
import com.fleamarket.common.utils.MD5Util;
import com.fleamarket.common.utils.MapperUtil;
import com.fleamarket.common.utils.PrefixKey;
import com.fasterxml.jackson.databind.ObjectMapper;

import cn.edu.scnu.user.mapper.UserMapper;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

@Service
public class UserService {
	@Autowired
	private UserMapper userMapper;
//	@Autowired
//	private ShardedJedisPool pool;
	@Autowired
	private JedisCluster jedis;
	private ObjectMapper mapper = MapperUtil.MP;
	public Integer checkUsername(String userName) {
		System.out.println("UserService");
		return userMapper.queryUsername(userName);
	}
	public void userSave(User user) {
//		user.setUserId(UUID.randomUUID().toString());
		user.setUserPassword(MD5Util.md5(user.getUserPassword()));
//		System.out.println(user.getUserPassword());
		String id=userMapper.userSave(user);
//		user.setUserId(id);
		
	}
	public String doLogin(User user) {
//		//获取连接池对象
//		ShardedJedis jedis = pool.getResource();
		try{
			//对password做加密操作
			user.setUserPassword(MD5Util.md5(user.getUserPassword()));
			//利用user数据查询数据库，判断登录是否合法
			User exist = userMapper.queryUserByNameAndPassword(user);
			System.out.println(exist);
			if(exist == null){
				System.out.println("exist==null");
				//登录失败，没有数据可以存储在redis
				return "";
			}else{
				//为了后续访问能够获取到user对象的数据，需要创建一个key值，将userJson作为value
				//存储在redis中，key值返回给前端
				//前端页面下次访问就可以携带生成一个cookie将要携带回去的ticket
				String ticket = PrefixKey.USER_LOGIN_TICKET+System.currentTimeMillis()+user.getUserId();
				System.out.println("ticket"+ticket);
				//String ticket = UUID.randomUUID().toString();
				String userJson;
				userJson = mapper.writeValueAsString(exist);
				//判断当前用户是否曾经有人登录过
				System.out.println("userJson:"+userJson);
				String existTicket=PrefixKey.USER_LOGINED_CHECK_PREFIX+exist.getUserId();
				System.out.println(existTicket);
				//顶替实现.不允许前一个登录的人ticket存在
//				if(StringUtils.isNotEmpty(existTicket)){
//					jedis.del(existTicket);
//				}
				//三个用户顶替,先获取未插入的长度
				long len = jedis.llen(existTicket);
				if(len>=2){
					String del = jedis.rpop(existTicket);
					jedis.del(del);
				}
				jedis.lpush(existTicket, ticket);
				//用户登录超时
				jedis.setex(ticket, 600, userJson);
//				//定义当前客户端登录的信息 userId:ticket
//				jedis.setex("user_logined_"+exist.getUserId(), 60*30,ticket);
				
				return ticket;
				}
		}catch(Exception e){
			e.printStackTrace();
			return "";
		}
	}
			
		
		//		//利用user数据查询数据库，判断登录是否合法
//		user.setUserPassword(MD5Util.md5(user.getUserPassword()));
//		User exist = userMapper.queryUserByNameAndPassword(user);
//		if(exist == null){
//			System.out.println("exist==null");
//			//登录失败，没有数据可以存储在redis
//			return "";
//		}else{
//			//为了后续访问能够获取到user对象的数据，需要创建一个key值，将userJson作为value
//			//存储在redis中，key值返回给前端
//			//前端页面下次访问就可以携带生成一个cookie将要携带回去的ticket
//			String ticket = UUID.randomUUID().toString();
//			//jackson代码将已存在的exist用户对象转化为json
//			ObjectMapper mapper = new ObjectMapper();
//			String userJson;
//			try{
//				userJson = mapper.writeValueAsString(exist);
//				System.out.println(userJson);
//			}catch(Exception e){
//				e.printStackTrace();
//				return "";
//			}
//			//将userJson存储在redis中
//			Jedis jedis = new Jedis("192.168.126.153",6379);
//			System.out.println(ticket);
//			jedis.set(ticket, userJson);
//			return ticket;
		
	
	public String queryUserJson(String ticket) {
		
//		ShardedJedis jedis = pool.getResource();
		String userJson = "";
		try{
			userJson = jedis.get(ticket);
			return userJson;
		}catch(Exception e){
			e.printStackTrace();
			return "";
		}
//		finally{
//			pool.returnResource(jedis);
//		}
	}
	public String logout(String ticket) {
		try{
			jedis.del(ticket);
			return "success";
		}catch (Exception e) {
			return null;
		}
	}

}
