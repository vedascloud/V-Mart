package com.vedas.vmart.model;

import java.util.ArrayList;

public class OrderInfo {
	
	  private String orderId;

	  public String getOrderId() { return this.orderId; }

	  public void setOrderId(String orderId) { this.orderId = orderId; }

	  private String userId;

	  public String getUserId() { return this.userId; }

	  public void setUserId(String userId) { this.userId = userId; }

	  private String paymentType;

	  public String getPaymentType() { return this.paymentType; }

	  public void setPaymentType(String paymentType) { this.paymentType = paymentType; }

	  private String deliveryType;

	  public String getDeliveryType() { return this.deliveryType; }

	  public void setDeliveryType(String deliveryType) { this.deliveryType = deliveryType; }

	  private String timeStamp;

	  public String getTimeStamp() { return this.timeStamp; }

	  public void setTimeStamp(String timeStamp) { this.timeStamp = timeStamp; }

	  private String totalCoast;

	  public String getTotalCoast() { return this.totalCoast; }

	  public void setTotalCoast(String totalCoast) { this.totalCoast = totalCoast; }

	  private String deliveryCharges;

	  public String getDeliveryCharges() { return this.deliveryCharges; }

	  public void setDeliveryCharges(String deliveryCharges) { this.deliveryCharges = deliveryCharges; }

	  private DeliveryInfo deliveryInfo;

	  public DeliveryInfo getDeliveryInfo() { return this.deliveryInfo; }

	  public void setDeliveryInfo(DeliveryInfo deliveryInfo) { this.deliveryInfo = deliveryInfo; }

	  private ArrayList<ItemsInfo> itemsInfo;

	  public ArrayList<ItemsInfo> getItemsInfo() { return this.itemsInfo; }

	  public void setItemsInfo(ArrayList<ItemsInfo> itemsInfo) { this.itemsInfo = itemsInfo; }
				
		
}
