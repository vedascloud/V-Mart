package com.vedas.vmart.dao;

import java.util.List;

import com.vedas.vmart.model.ContactUs;

public interface ContactUsDAO {
	
	//This method is used to Send Mail to App Team
	public List<ContactUs> sendMail(String contactUs) ;

}
