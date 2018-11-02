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

import com.vedas.vmart.dao.ContactUsDAO;
import com.vedas.vmart.model.ContactUs;

@RestController
public class ContactUsController {
	
	//Service which will do all data retrieval/manipulation work
	@Autowired
	private ContactUsDAO serviceContactUs;
	
	//---------------------------------------Send Mail to App Team-------------------------------------------------------------
	@RequestMapping(value = "/contactUs", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ContactUs>> send(@RequestBody String contactUs) {
		List<ContactUs> users = serviceContactUs.sendMail(contactUs);
        if(users == null){
        	 List<ContactUs> pl1 = new ArrayList<ContactUs>();
        	 ContactUs pl = new ContactUs();
	            pl.setMessage("Mail Not Sended");
	            pl.setResponse("0");
	            pl1.add(pl);
            return new ResponseEntity<List<ContactUs>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<ContactUs>>(users, HttpStatus.OK);
    }

}
