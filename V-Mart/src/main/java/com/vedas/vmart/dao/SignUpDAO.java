package com.vedas.vmart.dao;


import java.util.List;

import com.vedas.vmart.model.SignUp;

public interface SignUpDAO {
	
	public List<SignUp> signup(String mobileNumber);
	
	

}
