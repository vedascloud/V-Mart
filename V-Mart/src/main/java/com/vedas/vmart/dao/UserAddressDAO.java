package com.vedas.vmart.dao;

import java.util.List;

import com.vedas.vmart.model.UserAddress;
import com.vedas.vmart.model.UserAddressList;

public interface UserAddressDAO {
	
	//This method is used to add/insert Data  
	public List<UserAddressList> insert(UserAddress userAddress);
	
	//This method is used to update Data
	public List<UserAddressList> update(UserAddress userAddress);
	
	//This method is used to fetch Data
	public List<UserAddressList> select(String mobile);
	
	//This method is used to delete Data
	public List<UserAddressList> delete(UserAddress userAddress);


}
