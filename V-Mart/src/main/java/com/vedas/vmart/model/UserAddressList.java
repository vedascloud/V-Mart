package com.vedas.vmart.model;

import java.util.ArrayList;

public class UserAddressList {
	
	private String addressId;
	private String message;
	private String response;
	private ArrayList<UserAddress> addressList;
	
	public String getAddressId() {
		return addressId;
	}
	public void setAddressId(String addressId) {
		this.addressId = addressId;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getResponse() {
		return response;
	}
	public void setResponse(String response) {
		this.response = response;
	}
	public ArrayList<UserAddress> getAddressList() {
		return addressList;
	}
	public void setAddressList(ArrayList<UserAddress> addressList) {
		this.addressList = addressList;
	}
	
	
}
