package com.vedas.vmart.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.vedas.vmart.dao.UserAddressDAO;
import com.vedas.vmart.model.UserAddress;
import com.vedas.vmart.model.UserAddressList;

@RestController
public class UserAddressController {
	
	//Service which will do all data retrieval/manipulation work
	@Autowired
	UserAddressDAO addService;  
	
	//--------------Add UserAddress Data--------------------------------------------------------------------------------------------
	@RequestMapping(value = "/insertuseradrs", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UserAddressList>> userAddress(@RequestBody UserAddress userAddress) {
		List<UserAddressList> users = addService.insert(userAddress);
        if(users == null){
        	 List<UserAddressList> pl1 = new ArrayList<UserAddressList>();
        	 UserAddressList pl = new UserAddressList();
	            pl.setMessage("Address Not Added...");
	            pl.setResponse("0");
	            pl1.add(pl);
            return new ResponseEntity<List<UserAddressList>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<UserAddressList>>(users, HttpStatus.OK);
    }
	
	//-------------------------Update UserAddress Data--------------------------------------------------------------------------------
	@RequestMapping(value = "/updateuseradrs", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UserAddressList>> updateAddress(@RequestBody UserAddress userAddress) {
		List<UserAddressList> users = addService.update(userAddress);
        if(users == null){
        	 List<UserAddressList> pl1 = new ArrayList<UserAddressList>();
        	 UserAddressList pl = new UserAddressList();
	            pl.setMessage("Address Not Updated...");
	            pl.setResponse("0");
	            pl1.add(pl);
            return new ResponseEntity<List<UserAddressList>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<UserAddressList>>(users, HttpStatus.OK);
    }
	
	//-------------------------Fetch UserAddress Data--------------------------------------------------------------------------------
	@RequestMapping(value = "/getuseradrs/{mobile}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UserAddressList>> getCart(@PathVariable("mobile") String mobile) {
        List<UserAddressList> user = addService.select(mobile);
        		        
        if (user == null) {
            
            List<UserAddressList> pl1 = new ArrayList<UserAddressList>();
            UserAddressList pl = new UserAddressList();
            pl.setMessage("Addresses Not Available...");
            pl.setResponse("0");
            pl1.add(pl);
            return new ResponseEntity<List<UserAddressList>>(pl1,HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<UserAddressList>>(user,HttpStatus.OK);
    }
	
	 //-------------------------Delete UserAddress Data--------------------------------------------------------------------------------
	 @RequestMapping(value = "/deleteuseradrs",method = RequestMethod.DELETE,produces =  MediaType.APPLICATION_JSON_VALUE)
		 public ResponseEntity<List<UserAddressList>> addressdelete(@RequestBody UserAddress userAddress){
			 List<UserAddressList> user = addService.delete(userAddress);
        
        if (user == null) {
            List<UserAddressList> pl1 = new ArrayList<UserAddressList>();
            UserAddressList pl = new UserAddressList();
            pl.setMessage("Delete UserAddress Failed...");
            pl.setResponse("0");
            pl1.add(pl);
            return new ResponseEntity<List<UserAddressList>>(pl1,HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<UserAddressList>>(user,HttpStatus.OK);
		 }
	

}
