package com.project.loginservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.loginservice.dao.SellerLoginDao;
import com.project.loginservice.entity.SellerLoginEntity;
import com.project.loginservice.pojo.SellerLoginPojo;

@Service
public class SellerLoginServiceImpl implements SellerLoginService
{
	@Autowired
	SellerLoginDao sellerLoginDao;

	@Override
	public SellerLoginPojo validateSeller(SellerLoginPojo sellerLoginPojo)
	{
		SellerLoginEntity sellerLoginEntity = sellerLoginDao.findByUsernameAndPassword(sellerLoginPojo.getUsername(), sellerLoginPojo.getPassword());
	    sellerLoginPojo = new SellerLoginPojo(sellerLoginEntity.getUsername(),sellerLoginEntity.getPassword()); 
		return sellerLoginPojo;
	}
}
