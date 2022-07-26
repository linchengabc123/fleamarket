package cn.edu.scnu.user.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fleamarket.common.pojo.User;
import com.fleamarket.common.utils.CookieUtils;
import com.fleamarket.common.vo.SysResult;

import cn.edu.scnu.user.service.UserService;

@RestController
public class UserController {
	@Autowired
	private UserService userService;
	@RequestMapping("user/manage/checkUserName")
	public SysResult checkUsername(String userName){
		System.out.println("UserController");
		Integer exist = userService.checkUsername(userName);
		System.out.println(exist);
		if(exist==0){
			return SysResult.ok();
		}else{
			return SysResult.build(201, "已存在", null);
		}
	}
	@RequestMapping("/user/manage/save")
	public SysResult userSave(User user){
		Integer a = userService.checkUsername(user.getUserName());
		if(a > 0)
			return SysResult.build(201, "用户名已存在", null);
		try{
			userService.userSave(user);
			return SysResult.ok();
		}catch(Exception e){
			e.printStackTrace();
			return SysResult.build(201, e.getMessage(), null);
		}
				
	}
	@RequestMapping("/user/manage/logout/{ticket}")
	public SysResult logout(@PathVariable String ticket){
		System.out.println(ticket);
		String check = userService.logout(ticket);
		if(check == "success"){
			//确实曾经登陆过，也正在登陆使用状态中
			return SysResult.build(200, "0k", null);
		}else{
			return SysResult.build(201, "", null);
		}
	}
	@RequestMapping("/user/manage/login")
	public SysResult dologin(User user, HttpServletRequest request,
				HttpServletResponse response){
		//调用业务层确定合法且存储数据
		System.out.println("userController:");
		System.out.println(user);
		String ticket = userService.doLogin(user);
		System.out.println(ticket);
		//控制层将业务层存储登录成功的rediskey值
		//！"".equals(ticket)&&ticket!=null
		if(StringUtils.isNotEmpty(ticket)){
			//ticket非空，表示redis已经存好登录的查询结果
			//将ticket作为cookie的值返回，cookie的名称根据接口文件的规定来定义
			//调用CookieUtils工具类，将ticket添加到cookie返回给前端
			CookieUtils.setCookie(request, response, "EM_TICKET", ticket);
			return SysResult.ok();
		}else{
			return SysResult.build(201, "登录失败", null);
		}
	}
	
	@RequestMapping("/user/manage/query/{ticket}")
	public SysResult checkLoginUser(@PathVariable String ticket){
		System.out.println(ticket);
		String userJson = userService.queryUserJson(ticket);
		//判断非空
		System.out.println("ticket"+ticket);
		System.out.println("userJson:"+userJson);
		if(StringUtils.isNotEmpty(userJson)){
			//确实曾经登陆过，也正在登陆使用状态中
			return SysResult.build(200, "0k", userJson);
		}else{
			return SysResult.build(201, "", null);
		}
	}
}
