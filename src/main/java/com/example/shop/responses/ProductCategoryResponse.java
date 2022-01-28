package com.example.shop.responses;

import java.util.ArrayList;

import org.springframework.http.HttpStatus;

import com.example.shop.entity.ProductCategory;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductCategoryResponse {
	private String message;
	
	@JsonProperty("data")
	private ArrayList<ProductCategory> productCategoryList;
	
	@JsonProperty("status")
	private HttpStatus httpStatus;
	
	public ProductCategoryResponse(String message,HttpStatus httpStatus) {
		this.message = message;
		this.httpStatus = httpStatus;
		this.productCategoryList = null;
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public ArrayList<ProductCategory> getProductCategoryList() {
		return productCategoryList;
	}
	public void setProductCategoryList(ArrayList<ProductCategory> productCategoryList) {
		this.productCategoryList = productCategoryList;
	}
	public HttpStatus getHttpStatus() {
		return httpStatus;
	}
	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}
}
