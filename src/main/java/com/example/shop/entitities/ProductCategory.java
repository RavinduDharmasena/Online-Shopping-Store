package com.example.shop.entitities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.lang.Nullable;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;

@Entity(name = "SHOP_PRODUCT_CATEGORIES")
public class ProductCategory {
	@Id
	@Column(name = "CATEGORY_ID",nullable = false,insertable = false,updatable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("id")
	@Schema(description = "This will be assigned automatically")
	private long categoryID;

	@JsonProperty("name")
	@Column(name = "CATEGORY_NAME",nullable = false,insertable = true,updatable = true)
	@Schema(example = "Electronics",nullable = false,
	description = "Provide the category name",hidden = false)
	
	private String categoryName;

	@JsonProperty("description")
	@Column(name = "CATEGORY_DESCRIPTION",nullable = false,insertable = true,updatable = true)
	@Schema(example = "Best Electronic Items for Best Price",nullable = false,
	description = "Provide the category description",hidden = false)
	private String categoryDescription;

	@JsonProperty("parent_category_id")
	@Column(name = "PARENT_CATEGORY_ID",nullable = true,insertable = true,updatable = true)
	@Nullable
	@Schema(example = "2",nullable = true,
	description = "Provide the category id of the parent category",hidden = false)
	private String parentCategoryID;

	@JsonProperty("is_active")
	@Column(name = "CATEGORY_ACTIVE",nullable = false,insertable = true,updatable = true)
	@Schema(example = "1",nullable = false,
	description = "Provide whether category is active or deactive. 1 is active 0 "
			+ "is not active",hidden = false)
	private int categoryActive;
	
	@JsonProperty("is_deleted")
	@Column(name = "CATEGORY_DELETED",nullable = false,insertable = true,updatable = true)
	@Schema(example = "1",nullable = false,
	description = "Provide whether the category is deleted or not. 1 is deletd 0 "
			+ "is not deleted",hidden = false)
	private int categoryDeleted;

	public int getCategoryDeleted() {
		return categoryDeleted;
	}

	public void setCategoryDeleted(int categoryDeleted) {
		this.categoryDeleted = categoryDeleted;
	}

	public long getCategoryID() {
		return categoryID;
	}

	public void setCategoryID(long categoryID) {
		this.categoryID = categoryID;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getCategoryDescription() {
		return categoryDescription;
	}

	public void setCategoryDescription(String categoryDescription) {
		this.categoryDescription = categoryDescription;
	}

	public String getParentCategoryID() {
		return parentCategoryID;
	}

	public void setParentCategoryID(String parentCategoryID) {
		this.parentCategoryID = parentCategoryID;
	}

	public int getCategoryActive() {
		return categoryActive;
	}

	public void setCategoryActive(int categoryActive) {
		this.categoryActive = categoryActive;
	}
}
