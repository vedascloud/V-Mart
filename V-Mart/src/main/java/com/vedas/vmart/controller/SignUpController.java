package com.vedas.vmart.controller;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.vedas.vmart.dao.SignUpDAO;

import com.vedas.vmart.model.ProductList;
import com.vedas.vmart.model.SignUp;

@RestController
public class SignUpController {
	@Autowired
	SignUpDAO signupService;
	
	@RequestMapping(value = "/signup/{mobileNumber}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<SignUp>> signup(@PathVariable("mobileNumber") String mobileNumber) {
		List<SignUp> users = signupService.signup(mobileNumber);
        if(users == null){
        	 List<ProductList> pl1 = new ArrayList<ProductList>();
	            ProductList pl = new ProductList();
	            pl.setMessage("no data available");
	            pl.setResponse("0");
	            pl1.add(pl);
            return new ResponseEntity<List<SignUp>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<SignUp>>(users, HttpStatus.OK);
    }
	
	

}
