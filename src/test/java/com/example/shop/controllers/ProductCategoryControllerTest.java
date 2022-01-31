package com.example.shop.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.example.shop.entitities.ProductCategory;
import com.example.shop.repository.ProductCategoryRepository;
import com.example.shop.responses.ProductCategoryResponse;

@SpringBootTest
public class ProductCategoryControllerTest {
	
	@Autowired
	private ProductCategoryController productCategoryController;
	
	@MockBean
	private ProductCategoryRepository productCategoryRepository;
	
	private ProductCategory createProductCategory() {
		ProductCategory category = new ProductCategory();
		category.setCategoryActive(1);
		category.setCategoryDeleted(0);
		category.setCategoryDescription("Test Description");
		category.setCategoryName("Test Name");
		category.setParentCategoryID("2");
		
		return category;
	}
	
	@Test
	public void testProductCategoryNotExists() {
		ArrayList<ProductCategory> categoryList = new ArrayList<ProductCategory>();
		when(this.productCategoryRepository.findAll()).thenReturn(categoryList);	
		
		ResponseEntity<ProductCategoryResponse> response = this.productCategoryController.getProductCategories(-99);
		
		assertThat(response.getBody().getHttpStatus()).isEqualTo(HttpStatus.NOT_FOUND);
		assertThat(response.getBody().getMessage()).isEqualTo("Not Found");
		assertThat(response.getBody().getProductCategoryList()).isEqualTo(categoryList);
		assertThat(response.getStatusCodeValue()).isEqualTo(HttpStatus.NOT_FOUND.value());
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
	}
	
	@Test
	public void getProductCategories() {
		ArrayList<ProductCategory> categoryList = new ArrayList<ProductCategory>();
		categoryList.add(createProductCategory());
		categoryList.add(createProductCategory());
		categoryList.get(1).setCategoryID(3);
		when(this.productCategoryRepository.findAll()).thenReturn(categoryList);
		
		ResponseEntity<ProductCategoryResponse> response = this.productCategoryController.getProductCategories(-99);
		
		assertThat(response.getBody().getHttpStatus()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody().getMessage()).isEqualTo("success");
		assertThat(response.getBody().getProductCategoryList()).isEqualTo(categoryList);
		assertThat(response.getStatusCodeValue()).isEqualTo(HttpStatus.OK.value());
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
	}
	
	@Test
	public void testAddProductCategories() {
		when(this.productCategoryRepository.save(createProductCategory())).thenReturn(createProductCategory());
		
		ResponseEntity<ProductCategoryResponse> response = this.productCategoryController.addProductCategories(createProductCategory());
		
		assertThat(response.getBody().getHttpStatus()).isEqualTo(HttpStatus.CREATED);
		assertThat(response.getBody().getMessage()).isEqualTo("Created the category");
		assertThat(response.getStatusCodeValue()).isEqualTo(HttpStatus.CREATED.value());
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
	}
	
	@Test
	public void testUpdateExistingProductCategory() {
		Optional<ProductCategory> categoryOptional = Optional.of(createProductCategory());
		when(this.productCategoryRepository.findById(Integer.toUnsignedLong(5))).thenReturn(categoryOptional);
		ProductCategory category = this.createProductCategory();
		category.setCategoryDeleted(1);
		ResponseEntity<ProductCategoryResponse> response = this.productCategoryController.updateProductCategory(5,category);
		
		assertThat(response.getBody().getHttpStatus()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody().getMessage()).isEqualTo("Category is updated");
		assertThat(response.getStatusCodeValue()).isEqualTo(HttpStatus.OK.value());
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
	}
	
	@Test
	public void testUpdateNonExistingProductCategory() {
		Optional<ProductCategory> categoryOptional = Optional.empty();
		when(this.productCategoryRepository.findById(Integer.toUnsignedLong(5))).thenReturn(categoryOptional);
		ProductCategory category = this.createProductCategory();
		category.setCategoryDeleted(1);
		ResponseEntity<ProductCategoryResponse> response = this.productCategoryController.updateProductCategory(5,category);
		
		assertThat(response.getBody().getHttpStatus()).isEqualTo(HttpStatus.NOT_FOUND);
		assertThat(response.getBody().getMessage()).isEqualTo("Category is not found");
		assertThat(response.getStatusCodeValue()).isEqualTo(HttpStatus.NOT_FOUND.value());
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
	}
	
	@Test
	public void testDeleteExistingCategory() {
		Optional<ProductCategory> categoryOptional = Optional.of(createProductCategory());
		when(this.productCategoryRepository.findById(Integer.toUnsignedLong(5))).thenReturn(categoryOptional);
		doNothing().when(this.productCategoryRepository).deleteById(Integer.toUnsignedLong(5));

		ResponseEntity<ProductCategoryResponse> response = this.productCategoryController.deleteProductCategory(5);

		assertThat(response.getBody().getHttpStatus()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody().getMessage()).isEqualTo("Category is deleted");
		assertThat(response.getStatusCodeValue()).isEqualTo(HttpStatus.OK.value());
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
	}
	
	@Test
	public void testDeleteNonExistingCategory() {
		Optional<ProductCategory> categoryOptional = Optional.empty();
		when(this.productCategoryRepository.findById(Integer.toUnsignedLong(5))).thenReturn(categoryOptional);
		doNothing().when(this.productCategoryRepository).deleteById(Integer.toUnsignedLong(5));
		
		ResponseEntity<ProductCategoryResponse> response = this.productCategoryController.deleteProductCategory(5);

		assertThat(response.getBody().getHttpStatus()).isEqualTo(HttpStatus.NOT_FOUND);
		assertThat(response.getBody().getMessage()).isEqualTo("Category is not found");
		assertThat(response.getStatusCodeValue()).isEqualTo(HttpStatus.NOT_FOUND.value());
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
	}
}
