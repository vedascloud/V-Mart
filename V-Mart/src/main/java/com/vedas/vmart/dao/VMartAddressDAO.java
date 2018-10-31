package com.vedas.vmart.dao;

import java.util.List;

import com.vedas.vmart.model.VMartAddress;
import com.vedas.vmart.model.VMartAddressList;

public interface VMartAddressDAO {
	
	    //This method is used to fetch Data city wise
		public List<VMartAddressList> select(String wantedcity);
	
	    //This method is used to fetch Data
		public List<VMartAddressList> select();
		
		//This method is used to update Data
		public List<VMartAddressList> update(VMartAddress vMartAddress);

}
