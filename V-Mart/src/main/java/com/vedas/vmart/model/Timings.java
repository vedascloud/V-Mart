package com.vedas.vmart.model;

import java.util.ArrayList;

public class Timings {

	private String dayofWeek;
	private String date;
	
	public String getDayofWeek() {
		return dayofWeek;
	}
	public void setDayofWeek(String dayofWeek) {
		this.dayofWeek = dayofWeek;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	private ArrayList<TimeSlot> timeSlots;

	  public ArrayList<TimeSlot> getTimeSlots() { return this.timeSlots; }

	  public void setTimeSlots(ArrayList<TimeSlot> timeSlots) { this.timeSlots = timeSlots; }
	
	

}
