package com.vedas.vmart.dao;

import java.util.List;

import com.vedas.vmart.model.Cart;
import com.vedas.vmart.model.CartList;

public interface CartDAO {
	
	public List<CartList> addcart(Cart cart);
	
	public List<CartList> update(Cart cart);
	
	public List<CartList> delete(Cart cart);
	
	public List<CartList> getcart(String mobile);
	
	public List<CartList> deleteallcartitems(String mobile);

}
