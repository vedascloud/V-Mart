package com.vedas.vmart.dao;

import java.util.List;

import com.vedas.vmart.model.ProductList;

public interface ProductListDAO {
	
	public List<ProductList> list(String id);
	
	public List<ProductList> getall();
	
	
}
