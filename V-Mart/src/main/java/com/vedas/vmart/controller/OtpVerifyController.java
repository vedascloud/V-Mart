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

import com.vedas.vmart.dao.OtpVerificatioDAO;
import com.vedas.vmart.model.OtpVerification;
import com.vedas.vmart.model.OtpVerifyList;
@RestController
public class OtpVerifyController {
	
	@Autowired
	OtpVerificatioDAO otpservice;
	
	@RequestMapping(value = "/verify", method = RequestMethod.POST)
    public ResponseEntity<List<OtpVerifyList>> verifyotp(@RequestBody OtpVerification otp) {
		System.out.println("mobile number.."+otp.getMobileNumber());
		List<OtpVerifyList> users = otpservice.verify(otp);
        if(users == null){
        	System.out.println("empty list....");
        	 List<OtpVerifyList> pl1 = new ArrayList<OtpVerifyList>();
        	 OtpVerifyList pl = new OtpVerifyList();
	            pl.setMessage("no data available");
	            pl.setResponse("0");
	            pl1.add(pl);
            return new ResponseEntity<List<OtpVerifyList>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<OtpVerifyList>>(users, HttpStatus.OK);
    }

}
