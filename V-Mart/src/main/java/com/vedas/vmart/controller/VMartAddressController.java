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

import com.vedas.vmart.dao.VMartAddressDAO;
import com.vedas.vmart.model.VMartAddress;
import com.vedas.vmart.model.VMartAddressList;

@RestController
public class VMartAddressController {
	
	@Autowired
	VMartAddressDAO vmartaddService;
	
	//-------------------------Fetch V-Mart Address Data Using City--------------------------------------------------------------------------------
		@RequestMapping(value = "/vmartaddress/{wantedcity}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity<List<VMartAddressList>> getCart(@PathVariable("wantedcity") String wantedcity) {
	        List<VMartAddressList> user = vmartaddService.select(wantedcity);
	        		        
	        if (user == null) {
	            
	            List<VMartAddressList> pl1 = new ArrayList<VMartAddressList>();
	            VMartAddressList pl = new VMartAddressList();
	            pl.setMessage("Addresses Not Available...");
	            pl.setResponse("0");
	            pl1.add(pl);
	            return new ResponseEntity<List<VMartAddressList>>(pl1,HttpStatus.NOT_FOUND);
	        }
	        return new ResponseEntity<List<VMartAddressList>>(user,HttpStatus.OK);
	    }
	
	//-------------------------Fetch V-MartAddress Data--------------------------------------------------------------------------------
		@RequestMapping(value = "/vmartaddress", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity<List<VMartAddressList>> listVmartAdrs() {
	        List<VMartAddressList> user = vmartaddService.select();
	        		        
	        if (user == null) {
	            
	            List<VMartAddressList> pl1 = new ArrayList<VMartAddressList>();
	            VMartAddressList pl = new VMartAddressList();
	            pl.setMessage("Addresses Not Available...");
	            pl.setResponse("0");
	            pl1.add(pl);
	            return new ResponseEntity<List<VMartAddressList>>(pl1,HttpStatus.NOT_FOUND);
	        }
	        return new ResponseEntity<List<VMartAddressList>>(user,HttpStatus.OK);
	    }
		
		//-------------------------Update UserAddress Data--------------------------------------------------------------------------------
		@RequestMapping(value = "/vmartaddress", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity<List<VMartAddressList>> updateAddress(@RequestBody VMartAddress vMartAddress) {
			List<VMartAddressList> users = vmartaddService.update(vMartAddress);
	        if(users == null){
	        	 List<VMartAddressList> pl1 = new ArrayList<VMartAddressList>();
	        	 VMartAddressList pl = new VMartAddressList();
		            pl.setMessage("Address Not Updated...");
		            pl.setResponse("0");
		            pl1.add(pl);
	            return new ResponseEntity<List<VMartAddressList>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
	        }
	        return new ResponseEntity<List<VMartAddressList>>(users, HttpStatus.OK);
	    }

}
