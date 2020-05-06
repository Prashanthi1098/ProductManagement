package com.project.productservice.controller;

import org.apache.log4j.Logger;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.project.productservice.pojo.ProductPojo;
import com.project.productservice.service.ProductService;

import io.swagger.annotations.Api;

@CrossOrigin

@RestController
//@RequestMapping("product")
@Api
public class ProductController 
{
	static Logger LOG = Logger.getLogger(ProductController.class.getClass());

	@Autowired
	ProductService productService;

	@GetMapping("/prod/{prodId}")
	public ResponseEntity<ProductPojo> getProduct(@PathVariable("prodId") Integer prodId) 
	{
		LOG.info("Requested Received to getProduct");
		ProductPojo product = this.productService.getproduct(prodId);
		ResponseEntity<ProductPojo>response = new ResponseEntity<>(product,HttpStatus.OK);
		return response;
	}

	@GetMapping("/prod/all")
	@HystrixCommand(fallbackMethod = "getFallBackProductList")
	//public ResponseEntity<List<ProductPojo>> getAllProducts()
	ResponseEntity<?> getAllProducts() throws InterruptedException
	{
		LOG.info("Request Received to getAllProducts");
		 List<ProductPojo> allProducts = productService.getAllProducts();
		 //Thread.sleep(3000);
		 if(allProducts.size()!=0) 
		 {
			 return new ResponseEntity<List<ProductPojo>>(allProducts,HttpStatus.OK);
		 }
		 else
		 {
			 return new ResponseEntity<String>("There are no produts",HttpStatus.NOT_FOUND);
		 }
		
	}
	ResponseEntity<?> getFallBackProductList()
	{
		return new ResponseEntity<String>("Took time to respond",HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	
	@DeleteMapping("/deleteProd/{prodId}")
	public ResponseEntity<Boolean> deleteProduct(@PathVariable("prodId") Integer prodId)
	{
		LOG.info("Request Received to delete Product");

		 this.productService.deleteProduct(prodId);
		 ResponseEntity<Boolean> response = new ResponseEntity<>(true,HttpStatus.OK);
		return response;
	}
	
	@PostMapping("/addProd")
	ProductPojo addProduct(@RequestBody ProductPojo productPojo)
	{
		LOG.info("Request Received to add Product");

		return productService.addProduct(productPojo);

	}

	
	@PutMapping("/product")
	public ResponseEntity<Boolean> updateProduct(@RequestBody ProductPojo productPojo)
	{
		LOG.info("Request Received to update product");

		this.productService.upadateProduct(productPojo);
		ResponseEntity<Boolean> response = new ResponseEntity<>(true,HttpStatus.OK);
		return response;	
	}
}
