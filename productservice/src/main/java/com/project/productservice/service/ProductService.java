package com.project.productservice.service;

import java.util.List;

import com.project.productservice.pojo.ProductPojo;

public interface ProductService 
{

	ProductPojo getproduct(Integer prodId);

	List<ProductPojo> getAllProducts();

	void deleteProduct(Integer prodId);

	ProductPojo upadateProduct(ProductPojo productPojo);

	ProductPojo addProduct(ProductPojo productPojo);


}
