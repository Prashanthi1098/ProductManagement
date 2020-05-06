package com.project.productservice.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.productservice.dao.StoreDao;
import com.project.productservice.entity.ProductEntity;
import com.project.productservice.entity.StoreEntity;
import com.project.productservice.pojo.ProductPojo;
import com.project.productservice.pojo.StorePojo;

@Service
public class StoreServiceImpl implements StoreService
{
	static Logger LOG = Logger.getLogger(StoreServiceImpl.class.getClass());

	@Autowired
	StoreDao storeDao;

	@Override
	public StorePojo getStore(Integer storeId) 
	{
		LOG.info("Enterd getStores()");

//		StorePojo storePojo =  null;
//		Optional result = storeDao.findById(storeId);
//		if(result.isPresent())
//		{
//			StoreEntity storeEntity = (StoreEntity)result.get();
//			List<ProductEntity> productEntity = storeEntity.getAllProducts();
//			
//			storePojo = new StorePojo(storeEntity.getId(),storeEntity.getName(),storeEntity.getLocation(),storeEntity.getDate());
//			
//			
//		}
//
//		LOG.info("Exited getStores()");
		
		
//		StorePojo storePojo = null;
//		StoreEntity storeEntity = storeDao.findById(storeId).get();
//		if(storeEntity!=null)
//		{
//			List<ProductEntity> productEntity = storeEntity.getAllProducts();
//			//Set<ProductPojo> allProductsPojo = new HashSet<ProductPojo>();
//			StorePojo storePojo1 = new StorePojo(storeEntity.getId(),storeEntity.getName(),storeEntity.getLocation(),storeEntity.getDate());
//			return storePojo1;
//		}
		
		

		StorePojo storePojo =  null;
		Optional result = storeDao.findById(storeId);
		if(result.isPresent())
		{
			StoreEntity storeEntity = (StoreEntity)result.get();
			ProductEntity productEntity = (ProductEntity) storeEntity.getAllProducts();
			ProductPojo productPojo = new ProductPojo(productEntity.getId(),productEntity.getName(),productEntity.getImage(),productEntity.getPrice(),productEntity.getQuantity(),productEntity.getDescription(), storePojo);
			storePojo = new StorePojo(storeEntity.getId(),storeEntity.getName(),storeEntity.getLocation(),storeEntity.getDate());
			}

		return storePojo;
	}

	@Override
	public boolean addStore(StorePojo storePojo)
	{		
		StoreEntity storeEntity = new StoreEntity();
		storeEntity.setId(0);
		storeEntity.setName(storePojo.getName());
		storeEntity.setLocation(storePojo.getLocation());
		storeEntity.setDate(storePojo.getDate());
		storeEntity = storeDao.saveAndFlush(storeEntity);
		storePojo.setId(storeEntity.getId());
		
		return true;
	}
	

}
