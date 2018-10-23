package com.vedas.vmart.model;

import java.util.ArrayList;

public class Category {
	private String categoryName;

	  public String getCategoryName() { return this.categoryName; }

	  public void setCategoryName(String categoryName) { this.categoryName = categoryName; }

	  private ArrayList<Subcategory> subcategories;

	  public ArrayList<Subcategory> getSubcategories() { return this.subcategories; }

	  public void setSubcategories(ArrayList<Subcategory> subcategories) { this.subcategories = subcategories; }
}
