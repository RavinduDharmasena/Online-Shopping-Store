package com.example.shop.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.shop.entity.ProductCategory;
import com.example.shop.repository.ProductCategoryRepository;
import com.example.shop.responses.ProductCategoryResponse;

@RestController
public class ProductCategories {

	@Autowired
	private ProductCategoryRepository productCategoryRepository;
	
	@GetMapping(path = "/product_categories")
	public ResponseEntity<ProductCategoryResponse> getProductCategories() {
		List<ProductCategory> categories =  this.productCategoryRepository.findAll();
		ArrayList<ProductCategory> categoryList = new ArrayList<ProductCategory>();
		categoryList.addAll(categories);
		ProductCategoryResponse response = new ProductCategoryResponse("success",HttpStatus.OK);
		response.setProductCategoryList(categoryList);
		return new ResponseEntity<ProductCategoryResponse>(response,HttpStatus.OK);
	}
	
//	@GetMapping(path = "/product_categories_add")
//	public ResponseEntity<ProductCategoryResponse> addProductCategories() {
//		ProductCategory category = new ProductCategory();
//		category.setCategoryDescription("Test");
//		category.setCategoryName("Name");
//		category.setCategoryStatus(1);
//		category.setParentCategoryID("3");
//		this.productCategoryRepository.save(category);
//
//		ProductCategoryResponse response = new ProductCategoryResponse("message",HttpStatus.OK);
//		return new ResponseEntity<ProductCategoryResponse>(response,HttpStatus.OK);
//	}
}
