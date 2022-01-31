package com.example.shop.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.shop.entitities.ProductCategory;
import com.example.shop.repository.ProductCategoryRepository;
import com.example.shop.services.ProductCategoryService;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {

	@Autowired
	private ProductCategoryRepository productCategoryRepository;
	
	@Override
	public ProductCategory findProductsCategoryById(long id) {
		return this.productCategoryRepository.findById(id).get();
	}

	@Override
	public boolean checkProductCategoryExists(long id) {
		return this.productCategoryRepository.findById(id).isPresent();
	}

	@Override
	public ArrayList<ProductCategory> findAll() {
		List<ProductCategory> categories =  this.productCategoryRepository.findAll();
		ArrayList<ProductCategory> categoryList = new ArrayList<ProductCategory>();
		categoryList.addAll(categories);
		return categoryList;
	}

	@Override
	public void save(ProductCategory productCategory) {
		this.productCategoryRepository.save(productCategory);
	}

	@Override
	public void delete(long id) {
		this.productCategoryRepository.deleteById(id);
	}

	@Override
	public ArrayList<ProductCategory> findByCategoryActive(int activeStatus) {
		ArrayList<ProductCategory> categories = new ArrayList<ProductCategory>();
		categories.addAll(this.productCategoryRepository.findByCategoryActive(activeStatus));
		return categories;
	}

}
