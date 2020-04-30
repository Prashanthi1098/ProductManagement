package com.project.productservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.productservice.pojo.ProductPojo;
import com.project.productservice.pojo.StorePojo;
import com.project.productservice.service.StoreService;

@CrossOrigin

@RestController
@RequestMapping("product")

public class StoreController 
{

	@Autowired
	StoreService storeService;
	
	@GetMapping("/store/{storeId}")
	StorePojo getStores(@PathVariable("storeId") Integer storeId )
	{
		return storeService.getStores(storeId);
	}
	
	@PostMapping("/addStore")
	ProductPojo addStore(@RequestBody ProductPojo prodPojo)
	{
		return prodPojo;

	}

	
//	@GetMapping("/store/{storeId}")
//	List<StorePojo> getStore(@PathVariable("storeId") Integer storeId)
//	{
//		return (List<StorePojo>) storeService.getStores(storeId);
//	}
}
