package com.vedas.vmart.dao;

import java.util.List;


import com.vedas.vmart.model.OrdersList;

public interface OrdersListDAO {
	
	public List<OrdersList> insert(String json); //methos to insert orders

}
