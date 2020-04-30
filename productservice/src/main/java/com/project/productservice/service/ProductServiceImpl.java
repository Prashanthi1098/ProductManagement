package com.project.productservice.service;

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
	@Autowired
	ProductDao productDao;
	
	@Override
	public ProductPojo getproduct(Integer prodId) 
	{
		ProductPojo productPojo =  null;
		Optional result = productDao.findById(prodId);
		if(result.isPresent())
		{
			ProductEntity productEntity = (ProductEntity)result.get();
			StoreEntity storeEntity = productEntity.getStoreId();
			StorePojo storePojo = new StorePojo(storeEntity.getId(),storeEntity.getName(),storeEntity.getLocation(),storeEntity.getDate());
			productPojo = new ProductPojo(productEntity.getId(),productEntity.getName(),productEntity.getImage(),productEntity.getPrice(),productEntity.getQuantity(),productEntity.getDescription(),storePojo);
			
			}
		return productPojo;
	}
	
	
	@Override
	public List<ProductPojo> getAllProducts() 
	{
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
		return allProductPojo;
	}
	
	
	@Override
	public void deleteProduct(Integer prodId) 
	{
		
		productDao.deleteById(prodId);
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
		public ProductPojo upadateProduct(ProductPojo productPojo)
		{
			
			try {
			ProductPojo productPojo1 = new ProductPojo();
			StorePojo storePojo = productPojo1.getStoreId();
			StoreEntity storeEntity = new StoreEntity(storePojo.getId(),storePojo.getName(),storePojo.getLocation(),storePojo.getDate(),null);
			ProductEntity productEntity = new ProductEntity(productPojo1.getId(),productPojo1.getName(),productPojo1.getImage(),productPojo1.getPrice(),productPojo1.getQuantity(),productPojo1.getDescription(),storeEntity);
			productEntity = productDao.save(productEntity);
			productPojo.setId(productEntity.getId());

			}

			catch(NullPointerException e)
			{
				System.out.println("Null Pointer Exception");
			}
			return productPojo;
			
			
			
		
		}
	
}
