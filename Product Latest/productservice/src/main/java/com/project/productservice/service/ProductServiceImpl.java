package com.project.productservice.service;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.productservice.dao.ProductDao;
import com.project.productservice.entity.ProductEntity;
import com.project.productservice.entity.StoreEntity;
import com.project.productservice.pojo.ProductPojo;
import com.project.productservice.pojo.StorePojo;

@Service
public class ProductServiceImpl implements ProductService 
{
	static Logger LOG = Logger.getLogger(ProductServiceImpl.class.getClass());

	@Autowired
	ProductDao productDao;
	
	@Override
	public ProductPojo getproduct(Integer prodId) 
	{
		LOG.info("Enterd getproduct()");

		ProductPojo productPojo =  null;
		Optional result = productDao.findById(prodId);
		if(result.isPresent())
		{
			ProductEntity productEntity = (ProductEntity)result.get();
			StoreEntity storeEntity = productEntity.getStoreId();
			StorePojo storePojo = new StorePojo(storeEntity.getId(),storeEntity.getName(),storeEntity.getLocation(),storeEntity.getDate());
			productPojo = new ProductPojo(productEntity.getId(),productEntity.getName(),productEntity.getImage(),productEntity.getPrice(),productEntity.getQuantity(),productEntity.getDescription(),storePojo);
			
			}
		LOG.info("Exited getproduct()");

		return productPojo;
	}
	
	
	@Override
	public List<ProductPojo> getAllProducts() 
	{
		LOG.info("Enterd getAllProducts()");

		List<ProductPojo> allProductPojo =new ArrayList();
		Iterable<ProductEntity> allProductEntity = productDao.findAll();
		Iterator itr = allProductEntity.iterator();

		while(itr.hasNext()) 
		{
			ProductEntity productEntity  = (ProductEntity)itr.next();
			StoreEntity storeEntity = productEntity.getStoreId();
			StorePojo storePojo = new StorePojo(storeEntity.getId(),storeEntity.getName(),storeEntity.getLocation(),storeEntity.getDate());
			ProductPojo productPojo = new ProductPojo(productEntity.getId(),productEntity.getName(),productEntity.getImage(),productEntity.getPrice(),productEntity.getQuantity(),productEntity.getDescription(),storePojo);
			allProductPojo.add(productPojo);

		}
		LOG.info("Exited getAllProducts()");

		return allProductPojo;
	}
	
	
	@Override
	public boolean deleteProduct(Integer prodId) 
	{
		
		this.productDao.deleteById(prodId);
		return true;
	}
		
	
	@Override
	public ProductPojo addProduct(ProductPojo productPojo) 
	{
		StorePojo storePojo = productPojo.getStoreId();
		StoreEntity storeEntity = new StoreEntity(storePojo.getId(),storePojo.getName(),storePojo.getLocation(),storePojo.getDate(),null);
		
		ProductEntity productEntity = new ProductEntity();
		productEntity.setId(0);
		productEntity.setName(productPojo.getName());
		productEntity.setImage(productPojo.getImage());
		productEntity.setPrice(productPojo.getPrice());
		productEntity.setQuantity(productPojo.getQuantity());
		productEntity.setDescription(productPojo.getDescription());
		productEntity.setStoreId(storeEntity);
		
		productEntity = productDao.saveAndFlush(productEntity);
		productPojo.setId(productEntity.getId());
		return productPojo;
	}


		@Override
		public boolean upadateProduct(ProductPojo productPojo)
		{
			
			Optional<ProductEntity> productEntity = productDao.findById(productPojo.getId());
			if(productEntity.isPresent())
			{
				ProductEntity product = (ProductEntity) productEntity.get();
				product.setId(productPojo.getId());
				product.setName(productPojo.getName());
				product.setImage(productPojo.getImage());
				product.setPrice(productPojo.getPrice());
				product.setQuantity(productPojo.getQuantity());
				product.setDescription(productPojo.getDescription());
				productDao.save(product);
				return true;
			}
			return false;
			
		
		}
	
}
