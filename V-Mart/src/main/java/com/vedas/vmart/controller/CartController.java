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

import com.vedas.vmart.dao.CartDAO;
import com.vedas.vmart.model.Cart;
import com.vedas.vmart.model.CartList;

@RestController
public class CartController {
	
	@Autowired
	 private CartDAO cartService;
	 
	 		 @RequestMapping(value = "/cart", method = RequestMethod.POST,produces = "application/json")
			 public ResponseEntity<List<CartList>> addcarts(@RequestBody Cart cart) {
					 
		        List<CartList> user = cartService.addcart(cart);
		        
		        if (user == null) {
		            List<CartList> pl1 = new ArrayList<CartList>();
		            CartList pl = new CartList();
		            pl.setMessage("no products available");
		            pl.setResponse("0");
		            pl1.add(pl);
		            return new ResponseEntity<List<CartList>>(pl1,HttpStatus.NOT_FOUND);
		        }
		        return new ResponseEntity<List<CartList>>(user,HttpStatus.OK);
		 }
	 		 

	 		 @RequestMapping(value = "/cart", method = RequestMethod.PUT,produces = "application/json")
			 public ResponseEntity<List<CartList>> cartupdate(@RequestBody Cart cart) {
					 
		        List<CartList> user = cartService.update(cart);
		        
		        if (user == null) {
		            List<CartList> pl1 = new ArrayList<CartList>();
		            CartList pl = new CartList();
		            pl.setMessage("Update cart unsuccessfull");
		            pl.setResponse("0");
		            pl1.add(pl);
		            return new ResponseEntity<List<CartList>>(pl1,HttpStatus.NOT_FOUND);
		        }
		        return new ResponseEntity<List<CartList>>(user,HttpStatus.OK);
		 }
	 		 
	 		 @RequestMapping(value = "/cart",method = RequestMethod.DELETE,produces = "application/json")
	 		 public ResponseEntity<List<CartList>> cartdelete(@RequestBody Cart cart){
	 			 List<CartList> user = cartService.delete(cart);
		        
		        if (user == null) {
		            List<CartList> pl1 = new ArrayList<CartList>();
		            CartList pl = new CartList();
		            pl.setMessage("Delete cart unsuccessfull");
		            pl.setResponse("0");
		            pl1.add(pl);
		            return new ResponseEntity<List<CartList>>(pl1,HttpStatus.NOT_FOUND);
		        }
		        return new ResponseEntity<List<CartList>>(user,HttpStatus.OK);
	 		 }
	 		 
	 		@RequestMapping(value = "/cart/{mobile}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
		    public ResponseEntity<List<CartList>> getCart(@PathVariable("mobile") String mobile) {
		        List<CartList> user = cartService.getcart(mobile);
		        		        
		        if (user == null) {
		            
		            List<CartList> pl1 = new ArrayList<CartList>();
		            CartList pl = new CartList();
		            pl.setMessage("no carts available");
		            pl.setResponse("0");
		            pl1.add(pl);
		            return new ResponseEntity<List<CartList>>(pl1,HttpStatus.NOT_FOUND);
		        }
		        return new ResponseEntity<List<CartList>>(user,HttpStatus.OK);
		    }
	 		
	 		 @RequestMapping(value = "/cart/{mobile}",method = RequestMethod.DELETE,produces = "application/json")
	 		 public ResponseEntity<List<CartList>> allcartitemsdelete(@PathVariable("mobile") String mobile){
	 			 List<CartList> user = cartService.deleteallcartitems(mobile);
		        
		        if (user == null) {
		            List<CartList> pl1 = new ArrayList<CartList>();
		            CartList pl = new CartList();
		            pl.setMessage("Delete cart unsuccessfull");
		            pl.setResponse("0");
		            pl1.add(pl);
		            return new ResponseEntity<List<CartList>>(pl1,HttpStatus.NOT_FOUND);
		        }
		        return new ResponseEntity<List<CartList>>(user,HttpStatus.OK);
	 		 }

}
