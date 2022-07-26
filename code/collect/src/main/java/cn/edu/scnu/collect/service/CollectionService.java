package cn.edu.scnu.collect.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fleamarket.common.pojo.Product;
import com.fleamarket.common.vo.EasyUIResult;

import cn.edu.scnu.collect.mapper.CollectionMapper;


@Service
public class CollectionService {
	@Autowired
	private CollectionMapper collectionMapper;

	@Autowired
	private RestTemplate restTemplate;

	public EasyUIResult collectQuery(String userId) {
//		List<String> productIds = collectionMapper.collectQuery(userId);
//		System.out.println(productIds);
//		for (String pid : productIds) {
//			String responestr = restTemplate.getForObject("http://productservice/product/manage/item/"+pid, String.class);
//			System.out.println("返回信息："+responestr);
//		}
//		return null;
		EasyUIResult result = new EasyUIResult();
		System.out.println("zxc");
		List<Product>pList = collectionMapper.queryByPage(userId);
		result.setRows(pList);
		return result;
	}

	public void delCollection(String productId, String userId) {
		collectionMapper.delCollection(productId, userId);
	}

	public void addCollection(String productId, String userId) {
		collectionMapper.addCollection(productId, userId);
	}

	public Integer checkCollection(String productId, String userId) {
		return collectionMapper.checkCollection(productId, userId);
	}

}
