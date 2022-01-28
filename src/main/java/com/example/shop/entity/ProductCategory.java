package com.example.shop.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity(name = "SHOP_PRODUCT_CATEGORIES")
public class ProductCategory {
	@Id
	@Column(name = "CATEGORY_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("id")
	private int categoryID;

	@JsonProperty("name")
	@Column(name = "CATEGORY_NAME")
	private String categoryName;

	@JsonProperty("description")
	@Column(name = "CATEGORY_DESCRIPTION")
	private String categoryDescription;

	@JsonProperty("parent_category_id")
	@Column(name = "PARENT_CATEGORY_ID")
	private String parentCategoryID;

	@JsonProperty("status")
	@Column(name = "CATEGORY_STATUS")
	private int categoryStatus;

	public int getCategoryID() {
		return categoryID;
	}

	public void setCategoryID(int categoryID) {
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

	public int getCategoryStatus() {
		return categoryStatus;
	}

	public void setCategoryStatus(int categoryStatus) {
		this.categoryStatus = categoryStatus;
	}
}
