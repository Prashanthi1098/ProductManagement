package com.project.productservice.service;

import java.util.List;

import com.project.productservice.pojo.ProductPojo;
import com.project.productservice.pojo.StorePojo;

public interface StoreService {

	StorePojo getStore(Integer storeId);

	boolean addStore(StorePojo storePojo);




}
