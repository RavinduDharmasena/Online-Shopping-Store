package com.example.shop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.shop.entitities.ProductCategory;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long>{
	public List<ProductCategory> findByCategoryActive(int activeStatus);
}
