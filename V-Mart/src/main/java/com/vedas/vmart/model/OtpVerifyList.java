package com.vedas.vmart.model;

import java.util.ArrayList;

public class OtpVerifyList {
	
	private String response;
	private String message;
	private String cartId;
	
	public String getCartId() {
		return cartId;
	}
	public void setCartId(String cartId) {
		this.cartId = cartId;
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
	private ArrayList<Cart> cartData;

	  public ArrayList<Cart> getCartData() { return this.cartData; }

	  public void setCartData(ArrayList<Cart> cartData) { this.cartData = cartData; }

}
