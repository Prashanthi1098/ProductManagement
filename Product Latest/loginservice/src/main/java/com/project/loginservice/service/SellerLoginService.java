package com.project.loginservice.service;

import org.springframework.http.ResponseEntity;

import com.project.loginservice.pojo.SellerLoginPojo;

public interface SellerLoginService {

	ResponseEntity<SellerLoginPojo> validateSeller(SellerLoginPojo sellerLoginPojo);

}
