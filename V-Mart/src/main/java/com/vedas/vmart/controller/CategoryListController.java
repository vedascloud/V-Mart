package com.vedas.vmart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.vedas.vmart.dao.CategoryListDAO;
import com.vedas.vmart.model.CategoryList;

@RestController
public class CategoryListController {
	@Autowired
	 CategoryListDAO categoryService;  //Service which will do all data retrieval/manipulation work
	
	//-------------------Retrieve All Users--------------------------------------------------------
    
    @RequestMapping(value = "/categories", method = RequestMethod.GET)
    public ResponseEntity<List<CategoryList>> listAllUsers() {
        List<CategoryList> users = categoryService.list();
        if(users.isEmpty()){
            return new ResponseEntity<List<CategoryList>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<CategoryList>>(users, HttpStatus.OK);
    }
	 
}
