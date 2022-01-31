package com.example.shop.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import com.example.shop.entitities.ProductCategory;
import com.example.shop.repository.ProductCategoryRepository;

@SpringBootTest
public class ProductCategoryServiceTest {

	@MockBean
	private ProductCategoryRepository productCategoryRepository;
	
	@Autowired
	private ProductCategoryService productCategoryService;
	
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
	public void testFindProductsCategoryById() {
		ArrayList<ProductCategory> categoryList = new ArrayList<ProductCategory>();
		categoryList.add(createProductCategory());
		when(this.productCategoryRepository.findAll()).thenReturn(categoryList);
		assertThat(this.productCategoryService.findAll()).isEqualTo(categoryList);
	}
	
	@Test
	public void TestCheckProductCategoryExists() {
		Optional<ProductCategory> categoryOptional = Optional.of(createProductCategory());
		when(this.productCategoryRepository.findById(Integer.toUnsignedLong(5))).thenReturn(categoryOptional);
		assertThat(this.productCategoryService.checkProductCategoryExists(5)).isEqualTo(true);
	}

	@Test
	public void findAll() {
		ArrayList<ProductCategory> categoryList = new ArrayList<ProductCategory>();
		categoryList.add(createProductCategory());
		categoryList.add(createProductCategory());
		categoryList.get(1).setCategoryID(3);
		when(this.productCategoryRepository.findAll()).thenReturn(categoryList);
		assertThat(this.productCategoryService.findAll()).isEqualTo(categoryList);
	}
	
	@Test
	public void testFindByCategoryActive() {
		ArrayList<ProductCategory> categories = new ArrayList<ProductCategory>();
		categories.add(createProductCategory());
		when(this.productCategoryRepository.findByCategoryActive(1)).thenReturn(categories);
		
		assertThat(this.productCategoryService.findByCategoryActive(1)).isEqualTo(categories);
	}
	
	@Test
	public void testFindByCategoryDeactive() {
		ArrayList<ProductCategory> categories = new ArrayList<ProductCategory>();
		categories.add(createProductCategory());
		when(this.productCategoryRepository.findByCategoryActive(1)).thenReturn(categories);
		
		assertThat(this.productCategoryService.findByCategoryActive(1)).isEqualTo(categories);
	}
}
