package com.vedas.vmart.model;

import java.util.ArrayList;

public class TimingsList {
	
	private String response;
	private String message;
	private ArrayList<Timings> timings;
	
	public ArrayList<Timings> getTimings() {
		return timings;
	}
	public void setTimings(ArrayList<Timings> timings) {
		this.timings = timings;
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
