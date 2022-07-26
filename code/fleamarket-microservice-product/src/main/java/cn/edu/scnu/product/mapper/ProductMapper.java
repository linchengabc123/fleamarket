package cn.edu.scnu.product.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.fleamarket.common.pojo.Product;



public interface ProductMapper {
	//查找商品总数量
	public Integer queryTotal();
	//分页查询
	//多个参数时,可以使用@Param注解,定义参数在sql预编译时使用#{value值}
	public List<Product> queryByPage(@Param("start")Integer start,@Param("rows")Integer rows);
	public Product queryById(String prodId);
	public void productSave(Product product);
	public void productUpdate(Product product);
	public void productUpdatecategory(Product product);
	public void productDelete(@Param("ids")String ids);
	public void productInstock(@Param("ids")String ids);
	public void productReshelf(@Param("ids")String ids);
	public Integer queryTotal_category();
	public List<String> queryByPage_category(@Param("start")Integer start,@Param("rows") Integer rows);
	public Integer queryTotalsearch(@Param("page")Integer page,@Param("rows") Integer rows,@Param("productCategory") String productCategory, 
			@Param("productNum")int productNum, @Param("productSoldnum")int productSoldnum);
	public List<Product> queryByPagesearch(@Param("start")Integer start,@Param("rows") Integer rows,@Param("productCategory") String productCategory, 
			@Param("productNum")int productNum, @Param("productSoldnum")int productSoldnum);
	public Integer querycatTotal(@Param("productCategory")String productCategory);
	public List<Product> catqueryByPage(@Param("productCategory")String productCategory,@Param("start") Integer start,@Param("rows") Integer rows);
	public Integer productsearchtotal(@Param("query")String query);
	public List<Product> productsearch(@Param("query")String query, @Param("start")Integer start,@Param("rows") Integer rows);

}