package com.example.shop.services;

import java.util.ArrayList;

import com.example.shop.entitities.ProductCategory;

public interface ProductCategoryService {
	public ProductCategory findProductsCategoryById(long id);
	public boolean checkProductCategoryExists(long id);
	public ArrayList<ProductCategory> findAll();
	public ArrayList<ProductCategory> findByCategoryActive(int activeStatus);
	public void save(ProductCategory productCategory);
	public void delete(long id);
}
