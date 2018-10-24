package com.vedas.vmart.dao;

import java.util.List;

import com.vedas.vmart.model.UserAddress;
import com.vedas.vmart.model.UserAddressList;

public interface UserAddressDAO {
	
	//This method is used to add/insert UserAddress to Database
	public List<UserAddressList> insert(UserAddress userAddress);
	
	public List<UserAddressList> select(UserAddress userAddress);
	
	public List<UserAddressList> list();

}
