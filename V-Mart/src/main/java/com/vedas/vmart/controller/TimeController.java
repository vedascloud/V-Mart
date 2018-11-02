package com.vedas.vmart.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.vedas.vmart.dao.TimingsListDAO;
import com.vedas.vmart.model.TimingsList;

@RestController
public class TimeController {
	
	@Autowired
	private TimingsListDAO timeresource;
	
	 @RequestMapping(value = "/gettimeslot", method = RequestMethod.GET,produces = "application/json")
	 
	 public ResponseEntity<List<TimingsList>> addcarts() {
		 
	        List<TimingsList> user = timeresource.gettime();
	        
	        if (user == null) {
	            List<TimingsList> pl1 = new ArrayList<TimingsList>();
	            TimingsList pl = new TimingsList();
	            pl.setMessage("no products available");
	            pl.setResponse("0");
	            pl1.add(pl);
	            return new ResponseEntity<List<TimingsList>>(pl1,HttpStatus.NOT_FOUND);
	        }
	        return new ResponseEntity<List<TimingsList>>(user,HttpStatus.OK);
	 }

}
