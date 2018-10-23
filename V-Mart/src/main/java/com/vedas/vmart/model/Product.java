package com.vedas.vmart.model;

import java.util.ArrayList;

public class Product {
	private String productId;

	  public String getProductId() { return this.productId; }

	  public void setProductId(String productId) { this.productId = productId; }

	  private String itemName;

	  public String getItemName() { return this.itemName; }

	  public void setItemName(String itemName) { this.itemName = itemName; }

	  private ArrayList<Pinfo> pInfo;

	  public ArrayList<Pinfo> getPInfo() { return this.pInfo; }

	  public void setPInfo(ArrayList<Pinfo> pInfo) { this.pInfo = pInfo; }

	  private String description;

	  public String getDescription() { return this.description; }

	  public void setDescription(String description) { this.description = description; }
}
