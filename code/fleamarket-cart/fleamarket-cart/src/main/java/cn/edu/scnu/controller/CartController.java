package cn.edu.scnu.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fleamarket.common.pojo.Cart;
import com.fleamarket.common.pojo.User;
import com.fleamarket.common.vo.SysResult;

import cn.edu.scnu.service.CartService;

@RestController
@CrossOrigin
public class CartController {

	@Resource
	private CartService cartService;
	
	//添加商品进购物车
	@RequestMapping("cart/save")
	public SysResult addcart(String userId,String productId,Integer num) {
		Integer result=cartService.addcart(userId,productId,num);
		if(result==null||result==0) {
			return SysResult.build(501, "添加出错", null);
		}
		else {
			return SysResult.ok();
		}
	}
	
	//删除购物车中某条数据
	@RequestMapping("cart/delete")
	public SysResult cartdelete(String productId, String userId) {
		Integer result=cartService.cartdelete(productId,userId);
		if(result==null||result==0) {
			return SysResult.build(501, "删除错误", null);
		}
		return SysResult.ok();
	}
	
	//更新购物车中某条数据
	@RequestMapping("cart/update")
	public SysResult cartUpdate(String productId,String userId,Integer num) {
		Integer result=cartService.cartUpdate(productId,userId,num);
		if(result==null||result==0) {
			return SysResult.build(501, "更新错误", null);
		}
		return SysResult.ok();
	}
	
	//查询购物车中的数据
	@RequestMapping("cart/query")
	public SysResult cartquery(String userId) {
		List<Cart> cart=cartService.cartquery(userId);
		
		return SysResult.build(200, null, cart);
	}
	
	//根据id查商品
//		@RequestMapping("cart/getbyid")
//		public SysResult getbyid(String proid, String userId) {
//			List<Cart> cart=cartService.getbyid(proid, userId);
//			return SysResult.build(200, null, cart);
//		}

		@RequestMapping("cart/getbyid")
		public SysResult getbyid(@RequestParam("proId") String proid, @RequestParam("userId") String userId) {
				List<Cart> cart=cartService.getbyid(proid, userId);
				System.out.println(cart);
				return SysResult.build(200, null, cart);
		}

}
