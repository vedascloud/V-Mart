package com.vedas.vmart.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.vedas.vmart.dao.UserAddressDAO;
import com.vedas.vmart.model.CategoryList;
import com.vedas.vmart.model.UserAddress;
import com.vedas.vmart.model.UserAddressList;

@RestController
public class UserAddressController {
	
	@Autowired
	 UserAddressDAO addService;  //Service which will do all data retrieval/manipulation work
	
	@RequestMapping(value = "/addusertobook", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UserAddressList>> userAddress(@RequestBody UserAddress userAddress) {
		List<UserAddressList> users = addService.insert(userAddress);
        if(users == null){
        	 List<UserAddressList> pl1 = new ArrayList<UserAddressList>();
        	 UserAddressList pl = new UserAddressList();
	            pl.setMessage("No Data Available...");
	            pl.setResponse("0");
	            pl1.add(pl);
            return new ResponseEntity<List<UserAddressList>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<UserAddressList>>(users, HttpStatus.OK);
    }
	
	@RequestMapping(value = "/getuserbook", method = RequestMethod.GET)
    public ResponseEntity<List<UserAddressList>> listUsers(@RequestBody UserAddress userAddress) {
		List<UserAddressList> users = addService.select(userAddress);
        if(users == null){
        	 List<UserAddressList> pl1 = new ArrayList<UserAddressList>();
        	 UserAddressList pl = new UserAddressList();
	            pl.setMessage("No Data Available...");
	            pl.setResponse("0");
	            pl1.add(pl);
            return new ResponseEntity<List<UserAddressList>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<UserAddressList>>(users, HttpStatus.OK);
    }

	
	@RequestMapping(value = "/useraddrs/", method = RequestMethod.GET)
    public ResponseEntity<List<UserAddressList>> listAllUsers() {
        List<UserAddressList> users = addService.list();
        if(users.isEmpty()){
            return new ResponseEntity<List<UserAddressList>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<UserAddressList>>(users, HttpStatus.OK);
    }


}
