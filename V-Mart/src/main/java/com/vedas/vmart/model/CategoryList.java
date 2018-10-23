package com.vedas.vmart.model;

import java.util.ArrayList;

public class CategoryList {
	private String response;

	  public String getResponse() { return this.response; }

	  public void setResponse(String response) { this.response = response; }

	  private String message;

	  public String getMessage() { return this.message; }

	  public void setMessage(String message) { this.message = message; }

	  private ArrayList<Category> categories;

	  public ArrayList<Category> getCategories() { return this.categories; }

	  public void setCategories(ArrayList<Category> categories) { this.categories = categories; }
}
