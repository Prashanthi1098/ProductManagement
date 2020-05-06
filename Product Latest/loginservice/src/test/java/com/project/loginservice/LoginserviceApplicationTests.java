package com.project.loginservice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.project.loginservice.dao.SellerLoginDao;
import com.project.loginservice.entity.SellerLoginEntity;
import com.project.loginservice.pojo.SellerLoginPojo;
import com.project.loginservice.service.SellerLoginServiceImpl;

@SpringBootTest
class LoginserviceApplicationTests
{
	@Autowired
	private SellerLoginServiceImpl sellerLoginServiceImpl;
	
	@MockBean
	private SellerLoginDao sellerLoginDao;
	
	
	@Test
	void testvalidate() throws Exception
	{
		SellerLoginEntity sellerLoginEntity = new SellerLoginEntity("John","pass");
		SellerLoginPojo sellerLoginPojo = new SellerLoginPojo("John","pass");
		when(sellerLoginDao.save(sellerLoginEntity)).thenReturn(sellerLoginEntity);
		assertEquals(sellerLoginPojo,sellerLoginServiceImpl.validateSeller(sellerLoginPojo));
	}

}
