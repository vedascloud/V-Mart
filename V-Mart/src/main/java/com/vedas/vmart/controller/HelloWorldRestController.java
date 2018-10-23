package com.vedas.vmart.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.vedas.vmart.dao.ContactDAO;
import com.vedas.vmart.model.Contact;

@RestController
public class HelloWorldRestController {
	 @Autowired
	 ContactDAO userService;  //Service which will do all data retrieval/manipulation work
	 
	     
	  //-------------------Retrieve All Users--------------------------------------------------------
	     
	    @RequestMapping(value = "/user/", method = RequestMethod.GET)
	    public ResponseEntity<List<Contact>> listAllUsers() {
	        List<Contact> users = userService.list();
	        if(users.isEmpty()){
	            return new ResponseEntity<List<Contact>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
	        }
	        return new ResponseEntity<List<Contact>>(users, HttpStatus.OK);
	    }
	 
	
	    //-------------------Retrieve Single User--------------------------------------------------------
	     
	    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity<Contact> getUser(@PathVariable("id") long id) {
	        System.out.println("Fetching User with id " + id);
	        Contact user = userService.get((int) id);
	        if (user == null) {
	            System.out.println("User with id " + id + " not found");
	            return new ResponseEntity<Contact>(HttpStatus.NOT_FOUND);
	        }
	        return new ResponseEntity<Contact>(user, HttpStatus.OK);
	    }
	 
	     
	     
	    //-------------------Create a User--------------------------------------------------------
	     
	    @RequestMapping(value = "/user/", method = RequestMethod.POST)
	    public ResponseEntity<Void> createUser(@RequestBody Contact user,    UriComponentsBuilder ucBuilder) {
	        System.out.println("Creating User " + user.getName());
	 
	        /*if (userService.isUserExist(user)) {
	            System.out.println("A User with name " + user.getName() + " already exist");
	            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
	        }*/
	 
	        userService.saveOrUpdate(user);
	 
	        HttpHeaders headers = new HttpHeaders();
	        headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri());
	        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	    }
	 
	     
	    //------------------- Update a User --------------------------------------------------------
	     
	    @RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
	    public ResponseEntity<Contact> updateUser(@PathVariable("id") long id, @RequestBody Contact user) {
	        System.out.println("Updating User " + id);
	         
	        /*User currentUser = userService.findById(id);
	         
	        if (currentUser==null) {
	            System.out.println("User with id " + id + " not found");
	            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
	        }
	 
	        currentUser.setName(user.getName());
	        currentUser.setAge(user.getAge());
	        currentUser.setSalary(user.getSalary());
	         
	        userService.updateUser(currentUser);*/
	        userService.saveOrUpdate(user);
	        return new ResponseEntity<Contact>(user, HttpStatus.OK);
	    }
	 
	    //------------------- Delete a User --------------------------------------------------------
	     
	    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
	    public ResponseEntity<Contact> deleteUser(@PathVariable("id") long id) {
	        System.out.println("Fetching & Deleting User with id " + id);
	 
	        /*User user = userService.findById(id);
	        if (user == null) {
	            System.out.println("Unable to delete. User with id " + id + " not found");
	            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
	        }
	 
	        userService.deleteUserById(id);*/
	        userService.delete((int) id);
	        return new ResponseEntity<Contact>(HttpStatus.NO_CONTENT);
	    }
	 
	     
	   /* //------------------- Delete All Users --------------------------------------------------------
	     
	    @RequestMapping(value = "/user/", method = RequestMethod.DELETE)
	    public ResponseEntity<Contact> deleteAllUsers() {
	        System.out.println("Deleting All Users");
	 
	        userService.deleteAllUsers();
	        return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
	    }*/
}
