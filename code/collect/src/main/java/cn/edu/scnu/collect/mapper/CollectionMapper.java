package cn.edu.scnu.collect.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.fleamarket.common.pojo.Product;

public interface CollectionMapper {
	public void delCollection(@Param("productId") String productId, @Param("userId") String userId);
	public void addCollection(@Param("productId") String productId, @Param("userId") String userId);
	public List<Product> queryByPage(@Param("userId") String userId);
	public Integer checkCollection(@Param("productId") String productId, @Param("userId") String userId);
}