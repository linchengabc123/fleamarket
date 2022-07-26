package cn.edu.scnu.user.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.fleamarket.common.pojo.User;


public interface UserMapper{

	Integer queryUsername(String userName);

	String userSave(User user);

	User queryUserByNameAndPassword(User user);
	
}
