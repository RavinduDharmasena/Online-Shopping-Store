package com.example.shop.controllers;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.shop.entitities.ProductCategory;
import com.example.shop.responses.ProductCategoryResponse;
import com.example.shop.services.ProductCategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;


@RestController
public class ProductCategoryController {

	private static final Logger logger = LoggerFactory.getLogger(ProductCategoryController.class);
	
	@Autowired
	private ProductCategoryService produCategoryService;
	
	@ApiResponse(responseCode = "200", description = "Operation is successfull")
	@ApiResponse(responseCode = "500", description = "Internal Server Error")
	@Operation(description = "Gets all the product categories",method = "GET")
	@GetMapping(path = "/product_categories")
	public ResponseEntity<ProductCategoryResponse> getProductCategories(@RequestParam(name = "status",required = false,defaultValue = "-99") int status) {
		ProductCategoryResponse response = new ProductCategoryResponse();
		try {
			ArrayList<ProductCategory> categoryList = new ArrayList<ProductCategory>();
			if(status == -99) {				
				categoryList = this.produCategoryService.findAll();
			}
			else {
				categoryList = this.produCategoryService.findByCategoryActive(status);
			}

			response.setProductCategoryList(categoryList);
			response.setMessage("success");
			response.setHttpStatus(HttpStatus.OK);
			
			if(categoryList.size() > 0) {			
				return new ResponseEntity<ProductCategoryResponse>(response,HttpStatus.OK);
			}
			else {
				response.setMessage("Not Found");
				response.setHttpStatus(HttpStatus.NOT_FOUND);
				return new ResponseEntity<ProductCategoryResponse>(response,HttpStatus.NOT_FOUND);
			}
		}
		catch(Exception e) {
			response.setMessage("Internal Server Error");
			logger.error(e.getMessage());
			response.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
			return new ResponseEntity<ProductCategoryResponse>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiResponse(responseCode = "200", description = "Operation is successfull")
	@ApiResponse(responseCode = "500", description = "Internal Server Error")
	@PostMapping(path = "/product_category")
	public ResponseEntity<ProductCategoryResponse> addProductCategories(@RequestBody ProductCategory produCategory) {
		ProductCategoryResponse response = new ProductCategoryResponse();
		try {
			response.setMessage("Created the category");
			response.setHttpStatus(HttpStatus.CREATED);
			this.produCategoryService.save(produCategory);
			return new ResponseEntity<ProductCategoryResponse>(response,HttpStatus.CREATED);
		}
		catch(Exception e) {
			logger.debug(e.getMessage());
			response.setMessage("Internal Server Error");
			response.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
			return new ResponseEntity<ProductCategoryResponse>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiResponse(responseCode = "200", description = "Operation is successfull")
	@ApiResponse(responseCode = "500", description = "Internal Server Error")
	@ApiResponse(responseCode = "404", description = "Not Found")
	@PutMapping(path = "/product_category/{id}")
	public ResponseEntity<ProductCategoryResponse> updateProductCategory(@PathVariable(name = "id",required = true) long id,@RequestBody ProductCategory productCategory){
		ProductCategoryResponse response = new ProductCategoryResponse();
		try {
			response.setMessage("Category is updated");
			response.setHttpStatus(HttpStatus.OK);
			
			boolean isObjextExists = this.produCategoryService.checkProductCategoryExists(id);
			
			if(isObjextExists) {
				ProductCategory category = this.produCategoryService.findProductsCategoryById(id);
				category.setCategoryDescription(productCategory.getCategoryDescription());
				category.setCategoryActive(productCategory.getCategoryActive());
				category.setCategoryDeleted(productCategory.getCategoryDeleted());
				category.setCategoryName(productCategory.getCategoryName());
				
				System.out.println(productCategory.getParentCategoryID());
				if(productCategory.getParentCategoryID() != null) {
					category.setParentCategoryID(productCategory.getParentCategoryID());
				}
				this.produCategoryService.save(category);
				return new ResponseEntity<ProductCategoryResponse>(response,HttpStatus.OK);
			}
			else {
				response.setMessage("Category is not found");
				response.setHttpStatus(HttpStatus.NOT_FOUND);
				return new ResponseEntity<ProductCategoryResponse>(response,HttpStatus.NOT_FOUND);
			}
			
		}
		catch(Exception e) {
			logger.debug(e.getMessage());
			response.setMessage("Internal Server Error");
			response.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
			return new ResponseEntity<ProductCategoryResponse>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiResponse(responseCode = "200", description = "Operation is successfull")
	@ApiResponse(responseCode = "500", description = "Internal Server Error")
	@ApiResponse(responseCode = "404", description = "Not Found")
	@DeleteMapping(path = "/product_category/{id}")
	public ResponseEntity<ProductCategoryResponse> deleteProductCategory(@PathVariable(name = "id",required = true) long id){
		ProductCategoryResponse response = new ProductCategoryResponse();
		try {
			response.setMessage("Category is deleted");
			response.setHttpStatus(HttpStatus.OK);
			
			boolean isObjextExists = this.produCategoryService.checkProductCategoryExists(id);
			
			if(isObjextExists) {
				this.produCategoryService.delete(id);
				return new ResponseEntity<ProductCategoryResponse>(response,HttpStatus.OK);
			}
			else {
				response.setMessage("Category is not found");
				response.setHttpStatus(HttpStatus.NOT_FOUND);
				return new ResponseEntity<ProductCategoryResponse>(response,HttpStatus.NOT_FOUND);
			}
			
		}
		catch(Exception e) {
			logger.debug(e.getMessage());
			response.setMessage("Internal Server Error");
			response.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
			return new ResponseEntity<ProductCategoryResponse>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
