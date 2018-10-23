package com.vedas.vmart.model;

import java.util.ArrayList;

public class ProductList {
	private String response;

	  public String getResponse() { return this.response; }

	  public void setResponse(String response) { this.response = response; }

	  private String message;

	  public String getMessage() { return this.message; }

	  public void setMessage(String message) { this.message = message; }

	  private ArrayList<Product> products;

	  public ArrayList<Product> getProducts() { return this.products; }

	  public void setProducts(ArrayList<Product> products) { this.products = products; }
}
