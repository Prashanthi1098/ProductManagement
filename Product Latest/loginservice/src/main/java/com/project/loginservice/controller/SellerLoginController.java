package com.project.loginservice.controller;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.loginservice.pojo.SellerLoginPojo;
import com.project.loginservice.service.SellerLoginService;

import io.swagger.annotations.Api;

@RestController
//@RequestMapping("product")
@Api
public class SellerLoginController
{
	static Logger LOG = Logger.getLogger(SellerLoginController.class.getClass());

	@Autowired
	SellerLoginService sellerLoginService;
	//EndPoint to validate Seller
	@GetMapping("validate")
	public ResponseEntity<SellerLoginPojo> validateSeller(@RequestHeader("Authorization") String data)
	{
		BasicConfigurator.configure();
		LOG.info("Entered the end point \'validate\'");
		LOG.info("Exited end point \'validate\'");
		String token[] = data.split(":");
		SellerLoginPojo sellerLoginPojo = new  SellerLoginPojo();
		sellerLoginPojo.setUsername(token[0]);
		sellerLoginPojo.setPassword(token[1]);
		
		
		return this.sellerLoginService.validateSeller(sellerLoginPojo) ;
		
	}
}
