package cn.edu.scnu.product.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fleamarket.common.pojo.Product;
import com.fleamarket.common.vo.EasyUIResult;

import cn.edu.scnu.product.mapper.ProductMapper;
import redis.clients.jedis.ShardedJedisPool;

@Service
public class ProductService {
	@Autowired
	private ProductMapper productMapper;
	
	public EasyUIResult productPageQuery(Integer page, Integer rows) {
		// TODO Auto-generated method stub
		// 封装数据到EasyUIResult对象
		// 1.创建一个返回的对象,将查询数据set进去然后返回
		EasyUIResult result = new EasyUIResult();
		// 2封装第一个属性 total 的数量
		System.out.println("zxc");
		Integer total = productMapper.queryTotal();
		System.out.println("total:"+total);
		// 3封装第二个属性List<Product> pList
		// 根据页数计算起始位置
		Integer start = (page - 1)  * rows;
		List<Product>pList = productMapper.queryByPage(start,rows);
		// 4封装对象属性
		result.setTotal(total);
		result.setRows(pList);
		return result;
	}

	public Product queryById(String prodId) {
		return productMapper.queryById(prodId);
		
	}

	public void productSave(Product product) {
		product.setProductId(UUID.randomUUID().toString());
		productMapper.productSave(product);
		
	}

	public void productUpdate(Product product) {
		productMapper.productUpdate(product);
	}


	public void productDelete(String ids) {
		// TODO Auto-generated method stub
		productMapper.productDelete(ids);
	}

	public void productInstock(String ids) {
		// TODO Auto-generated method stub
		productMapper.productInstock(ids);
	}

	public void productReshelf(String ids) {
		// TODO Auto-generated method stub
		productMapper.productReshelf(ids);
	}

	public void productUpdatecategory(Product product) {
		// TODO Auto-generated method stub
		productMapper.productUpdatecategory(product);
	}

	public EasyUIResult productPageQuerysearch(Integer page, Integer rows, String productCategory, 
			int productNum, int productSoldnum) {
				EasyUIResult result = new EasyUIResult();
				// 2封装第一个属性 total 的数量
				System.out.println("zxc");
				Integer total = productMapper.queryTotalsearch(page, rows,  productCategory, 
						 productNum, productSoldnum);
				System.out.println("total:"+total);
				// 3封装第二个属性List<Product> pList
				// 根据页数计算起始位置
				Integer start = (page - 1)  * rows;
				List<Product>pList = productMapper.queryByPagesearch(start, rows,  productCategory,  
						 productNum, productSoldnum);
				// 4封装对象属性
				System.out.println(pList.get(0));
				result.setTotal(total);
				result.setRows(pList);
				return result;
	}

	public EasyUIResult catproductPageQuery(String productCategory,Integer page, Integer rows) {
				EasyUIResult result = new EasyUIResult();
				// 2封装第一个属性 total 的数量
				System.out.println("zxcc");
				Integer total = productMapper.querycatTotal(productCategory);
				System.out.println("total:"+total);
				// 3封装第二个属性List<Product> pList
				// 根据页数计算起始位置
				Integer start = (page - 1)  * rows;
				List<Product>pList = productMapper.catqueryByPage(productCategory,start,rows);
				// 4封装对象属性
				result.setTotal(total);
				result.setRows(pList);
				return result;
	}

	public EasyUIResult productsearch(String query, Integer page, Integer rows) {
		EasyUIResult result = new EasyUIResult();
		// 2封装第一个属性 total 的数量
		System.out.println("searchproduct");
		Integer total = productMapper.productsearchtotal(query);
		System.out.println("total:"+total);
		// 3封装第二个属性List<Product> pList
		// 根据页数计算起始位置
		Integer start = (page - 1)  * rows;
		List<Product>pList = productMapper.productsearch(query,start,rows);
		// 4封装对象属性
		result.setTotal(total);
		result.setRows(pList);
		return result;
	}

	
	
}
