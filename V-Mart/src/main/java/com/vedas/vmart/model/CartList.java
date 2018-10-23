package com.vedas.vmart.model;

import java.util.ArrayList;

public class CartList {
	
	private String response;
	private String message;
	private ArrayList<Cart> cartList;
	

	public ArrayList<Cart> getCartList() {
		return cartList;
	}
	public void setCartList(ArrayList<Cart> cartList) {
		this.cartList = cartList;
	}
	public String getResponse() {
		return response;
	}
	public void setResponse(String response) {
		this.response = response;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	

}
