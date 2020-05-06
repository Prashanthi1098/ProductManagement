package com.project.loginservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.loginservice.entity.SellerLoginEntity;

public interface SellerLoginDao extends JpaRepository<SellerLoginEntity,Integer>
{
	SellerLoginEntity findByUsernameAndPassword(String username,String password);
}
