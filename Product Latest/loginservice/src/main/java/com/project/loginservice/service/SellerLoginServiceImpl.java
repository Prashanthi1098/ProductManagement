package com.project.loginservice.service;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project.loginservice.dao.SellerLoginDao;
import com.project.loginservice.entity.SellerLoginEntity;
import com.project.loginservice.exception.SellerNotFoundException;
import com.project.loginservice.pojo.SellerLoginPojo;

@Service
public class SellerLoginServiceImpl implements SellerLoginService
{
	static Logger LOG = Logger.getLogger(SellerLoginServiceImpl.class.getClass());

	@Autowired
	SellerLoginDao sellerLoginDao;

	@Override
	public ResponseEntity<SellerLoginPojo> validateSeller(SellerLoginPojo sellerLoginPojo)
	{
		LOG.info("Enterd validateSeller()");

		SellerLoginEntity sellerLoginEntity = sellerLoginDao.findByUsernameAndPassword(sellerLoginPojo.getUsername(), sellerLoginPojo.getPassword());
		if(sellerLoginEntity!=null)
		{
	    sellerLoginPojo = new SellerLoginPojo(sellerLoginEntity.getUsername(),null); 
	    ResponseEntity<SellerLoginPojo> response = new ResponseEntity<SellerLoginPojo>(sellerLoginPojo,HttpStatus.OK);
		LOG.info("Exited validateSeller()");
	    BasicConfigurator.resetConfiguration();
	    return response;
		}
		else
		{
			throw new SellerNotFoundException(sellerLoginPojo.getUsername());
		}
	}
}
