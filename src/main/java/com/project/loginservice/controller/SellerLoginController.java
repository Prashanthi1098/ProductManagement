package com.project.loginservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.loginservice.pojo.SellerLoginPojo;
import com.project.loginservice.service.SellerLoginService;

@RestController
@RequestMapping("product")
public class SellerLoginController
{
	@Autowired
	SellerLoginService sellerLoginService;
	//EndPoint to validate Seller
	@GetMapping("validate")
	SellerLoginPojo validateSeller(@RequestHeader("Authorization") String data)
	{
		String token[] = data.split(":");
		SellerLoginPojo sellerLoginPojo = new  SellerLoginPojo();
		sellerLoginPojo.setUsername(token[0]);
		sellerLoginPojo.setPassword(token[1]);
		
		
		return sellerLoginService.validateSeller(sellerLoginPojo) ;
		
	}
}
