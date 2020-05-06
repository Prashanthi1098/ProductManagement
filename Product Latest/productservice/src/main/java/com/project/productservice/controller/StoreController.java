package com.project.productservice.controller;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.productservice.pojo.ProductPojo;
import com.project.productservice.pojo.StorePojo;
import com.project.productservice.service.StoreService;

import io.swagger.annotations.Api;

@CrossOrigin

@RestController
//@RequestMapping("product")
@Api
public class StoreController 
{
	static Logger LOG = Logger.getLogger(StoreController.class.getClass());

	@Autowired
	StoreService storeService;
	
	@GetMapping("/store/{storeId}")
	StorePojo getStore(@PathVariable("storeId") Integer storeId )
	{
		LOG.info("Entered the end point \'store/{storeId}\'");
		LOG.info("Exited end point \'store/{storeId}\'");

		return storeService.getStore(storeId);
	}
	
	@PostMapping("/addStore")
	public ResponseEntity<Boolean> addStore(@RequestBody StorePojo storePojo)
	{
		LOG.info("Request Received to add Store");

		 storeService.addStore(storePojo);
		 ResponseEntity<Boolean> response = new ResponseEntity<>(true,HttpStatus.OK);
		 return response;
	}

	
}
