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
import com.vedas.vmart.dao.ProductListDAO;
import com.vedas.vmart.model.ProductList;

@RestController
public class ProductController {
	@Autowired
	 ProductListDAO productService;  //Service which will do all data retrieval/manipulation work
	
	//-------------------Retrieve All Users--------------------------------------------------------
   
	@RequestMapping(value = "/product/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity<List<ProductList>> getUser(@PathVariable("id") String id) {
	        System.out.println("Fetching User with id " + id);
	        List<ProductList> user = productService.list(id);
	        //System.out.println("Fetching User with id " + user.size());
	        
	        if (user == null) {
	            System.out.println("Product with id " + id + " not found");
	            List<ProductList> pl1 = new ArrayList<ProductList>();
	            ProductList pl = new ProductList();
	            pl.setMessage("no products available");
	            pl.setResponse("0");
	            pl1.add(pl);
	            return new ResponseEntity<List<ProductList>>(pl1,HttpStatus.NOT_FOUND);
	        }
	        return new ResponseEntity<List<ProductList>>(user,HttpStatus.OK);
	    }
	
	
	@RequestMapping(value = "/productlist", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ProductList>> getUser() {
       
        List<ProductList> user = productService.getall();
        //System.out.println("Fetching User with id " + user.size());
        
        if (user == null) {
            List<ProductList> pl1 = new ArrayList<ProductList>();
            ProductList pl = new ProductList();
            pl.setMessage("no products available");
            pl.setResponse("0");
            pl1.add(pl);
            return new ResponseEntity<List<ProductList>>(pl1,HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<ProductList>>(user,HttpStatus.OK);
    }
	
}
