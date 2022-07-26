package cn.edu.scnu.collect.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.fleamarket.common.vo.EasyUIResult;
import com.fleamarket.common.vo.SysResult;

import cn.edu.scnu.collect.service.CollectionService;


@RestController
public class CollectionController {

	@Autowired
	private CollectionService collectionService;

	@RequestMapping("/collect/manage/showCollection/{userId}") // 显示收藏
	public EasyUIResult collectQuery(@PathVariable String userId) {
		EasyUIResult result = collectionService.collectQuery(userId);
		return result;
		
	}

	@RequestMapping("/collect/manage/delCollection") // 取消收藏
	public SysResult delCollection(String productId, String userId) {
		try {
			collectionService.delCollection(productId, userId);
			return SysResult.ok();
		} catch (Exception e) {
			e.printStackTrace();
			return SysResult.build(201, e.getMessage(), null);
		}
	}

	@RequestMapping("/collect/manage/addCollection") // 添加收藏
	public SysResult addCollection(String productId, String userId) {
		System.out.println("productId:" + productId + ", userId:" + userId);
		try {
			collectionService.addCollection(productId, userId);
			return SysResult.ok();
		} catch (Exception e) {
			e.printStackTrace();
			return SysResult.build(201, e.getMessage(), null);
		}
	}
	
	
	@RequestMapping("/collect/manage/checkCollection") // 添加收藏
	public SysResult checkCollection(String productId, String userId) {
		System.out.println("productId:" + productId + ", userId:" + userId);
		try {
			Integer exist = collectionService.checkCollection(productId, userId);
			if(exist!=null){
				return SysResult.ok();
			}else{
			    return SysResult.build(202, "defeat", null);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return SysResult.build(201, e.getMessage(), null);
		}
	}

}
