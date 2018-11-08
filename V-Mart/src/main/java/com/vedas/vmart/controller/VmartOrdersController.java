package com.vedas.vmart.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.vedas.vmart.dao.OrdersListDAO;
import com.vedas.vmart.model.OrdersList;

@RestController
public class VmartOrdersController {
	
	@Autowired
	private OrdersListDAO vmartorderService;
	
	 @RequestMapping(value = "/orders", method = RequestMethod.POST,produces = "application/json")
	 public ResponseEntity<List<OrdersList>> addcarts(@RequestBody String orders) {
		 
        List<OrdersList> user = vmartorderService.insert(orders);
        
        if (user == null) {
            List<OrdersList> pl1 = new ArrayList<OrdersList>();
            OrdersList pl = new OrdersList();
            pl.setMessage("order not placed successfull");
            pl.setResponse("0");
            pl1.add(pl);
            return new ResponseEntity<List<OrdersList>>(pl1,HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<OrdersList>>(user,HttpStatus.OK);
 }

}
