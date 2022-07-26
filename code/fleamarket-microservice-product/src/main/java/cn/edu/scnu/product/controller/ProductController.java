package cn.edu.scnu.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fleamarket.common.pojo.Product;
import com.fleamarket.common.vo.EasyUIResult;
import com.fleamarket.common.vo.SysResult;

import cn.edu.scnu.product.service.ProductService;
import redis.clients.jedis.JedisCluster;


@RestController
public class ProductController {
	@Autowired
	private ProductService productService;
	@Autowired
	private JedisCluster jedis;
//	@Autowired
//	private ShardedJedisPool pool;
	@RequestMapping("/product/manage/pageManage")
	public EasyUIResult productPageQuery(Integer page,Integer rows){
		System.out.println("asd");	
		EasyUIResult result = productService.productPageQuery(page,rows);
			return result;
		}
	/*搜索商品*/
	@RequestMapping("/product/manage/catpageManage")
	public EasyUIResult catproductPageQuery(String productCategory,Integer page,Integer rows){
		System.out.println("assd");	
		EasyUIResult result = productService.catproductPageQuery(productCategory,page,rows);
			return result;
		}
	@RequestMapping("/product/manage/search")
	public EasyUIResult productsearch(String query,Integer page,Integer rows){
		System.out.println("assd");	
		EasyUIResult result = productService.productsearch(query,page,rows);
			return result;
		}
	
	@RequestMapping("/product/manage/pageManagesearch")
	public EasyUIResult productPageQuerysearch(Integer page,Integer rows,String productCategory,int productNum ,int productSoldnum){
		System.out.println("asdd");	
		EasyUIResult result = productService.productPageQuerysearch(page,rows,productCategory,productNum ,productSoldnum);
			return result;
		}
	//查看商品详情
	@RequestMapping("/product/manage/item/{prodId}")
	public String queryById(@PathVariable String prodId){
		//模拟用户发起访问
		System.out.println("用户请求查询商品详情，productId："+prodId);
		//生成productKey
		String productKey = "product_"+prodId;
		String productJson;
		//判断redis中是否存在数据，启动一个jedis客户端
//		ShardedJedis jedis = pool.getResource();
		//测试时如果前面有数据，可以先删除缓存数据
		//jedis.del(productKey);
		if(jedis.exists(productKey)){
			System.out.println("获取缓存中的数据："+jedis.get(productKey));
			productJson = jedis.get(productKey);
		}
		else{
			System.out.println("到数据库查询数据，productId="+prodId);
			//从数据库中查到的信息
			Product product = productService.queryById(prodId);
			ObjectMapper mapper = new ObjectMapper();
			
			try {
				productJson = mapper.writeValueAsString(product);
			} catch (Exception e) {
				e.printStackTrace();
				return "";
			}
			//String productJson = "{'name':'荣耀Play5T','price':1199,'category':'手机数码'}";
			System.out.println("将数据存入缓存，商品信息为："+productJson);
			jedis.set(productKey, productJson);
		}
		return productJson;
	}
	//商品新增
	@RequestMapping("/product/manage/save")
	public SysResult productSave(Product product){
		try{
			productService.productSave(product);
			return SysResult.ok();
		}catch(Exception e){
			e.printStackTrace();
			return SysResult.build(201, e.getMessage(), null);
		}
		}
	//商品修改
	@RequestMapping("/product/manage/update")
	public SysResult productUpdate(Product product){
		try{
			productService.productUpdate(product);
			return SysResult.ok();
		}catch(Exception e){
			e.printStackTrace();
			return SysResult.build(201, e.getMessage(), null);
		}
	}
	@RequestMapping("/product/manage/updatecategory")
	public SysResult productUpdatecategory(Product product){
		try{
			System.out.println("进入updatecategory");
			productService.productUpdatecategory(product);
			return SysResult.ok();
		}catch(Exception e){
			e.printStackTrace();
			return SysResult.build(201, e.getMessage(), null);
		}
	}
	@RequestMapping("/product/manage/delete")
	public SysResult productDelete(String ids){
		try{
			System.out.println(ids);
			productService.productDelete(ids);
			return SysResult.ok();
		}catch(Exception e){
			e.printStackTrace();
			return SysResult.build(201, e.getMessage(), null);
		}
	}
	@RequestMapping("/product/manage/instock")
	public SysResult productInstock(String ids){
		try{
			System.out.println(ids);
			productService.productInstock(ids);
			return SysResult.ok();
		}catch(Exception e){
			e.printStackTrace();
			return SysResult.build(201, e.getMessage(), null);
		}
	}
	@RequestMapping("/product/manage/reshelf")
	public SysResult productReshelf(String ids){
		try{
			System.out.println(ids);
			productService.productReshelf(ids);
			return SysResult.ok();
		}catch(Exception e){
			e.printStackTrace();
			return SysResult.build(201, e.getMessage(), null);
		}
	}
	
	
	
	}


	
	