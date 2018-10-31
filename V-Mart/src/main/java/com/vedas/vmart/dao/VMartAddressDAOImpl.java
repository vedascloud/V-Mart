package com.vedas.vmart.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.vedas.vmart.model.VMartAddress;
import com.vedas.vmart.model.VMartAddressList;

public class VMartAddressDAOImpl implements VMartAddressDAO{
	
private JdbcTemplate jdbcTemplate;
	
	public VMartAddressDAOImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public List<VMartAddressList> select(String wantedcity) {		
		ArrayList<VMartAddressList>	list = new ArrayList<>();
		VMartAddressList ul = new VMartAddressList(); 
		String join = "select * from vmartaddressbook where city = ?";							
		@SuppressWarnings("unused")
		List<VMartAddressList> cart = jdbcTemplate.query(join, new Object[] {wantedcity},new ResultSetExtractor<List<VMartAddressList>>() {
			@Override
			public List<VMartAddressList> extractData(ResultSet rs1) throws SQLException, DataAccessException {
											
				ArrayList<VMartAddress> al = new ArrayList<VMartAddress>();
				while(rs1.next()) {
					VMartAddress ua = new VMartAddress();
					ua.setAddressId(rs1.getString(1));
					ua.setAddress(rs1.getString(2));
					ua.setLandmark(rs1.getString(3));
					ua.setArea(rs1.getString(4));
					ua.setCity(rs1.getString(5));
					ua.setPincode(rs1.getString(6));
					ua.setLangitude(rs1.getString(7));
					ua.setLatitude(rs1.getString(8));
					ua.setFavourite(rs1.getBoolean(9));
				
					al.add(ua);
				}
				ul.setAddressList(al);
				return null;							
			}							
		});
	    ul.setResponse("3");
		ul.setMessage("Your Address Book Data");
		list.add(ul);
		return list;					
}

	@Override
	public List<VMartAddressList> select() {		
		ArrayList<VMartAddressList>	list = new ArrayList<>();
		VMartAddressList ul = new VMartAddressList(); 
		String join = "select * from vmartaddressbook" ;							
		@SuppressWarnings("unused")
		List<VMartAddressList> cart = jdbcTemplate.query(join,new ResultSetExtractor<List<VMartAddressList>>() {
			@Override
			public List<VMartAddressList> extractData(ResultSet rs1) throws SQLException, DataAccessException {
											
				ArrayList<VMartAddress> al = new ArrayList<VMartAddress>();
				while(rs1.next()) {
					VMartAddress ua = new VMartAddress();
					ua.setAddressId(rs1.getString(1));
					ua.setAddress(rs1.getString(2));
					ua.setLandmark(rs1.getString(3));
					ua.setArea(rs1.getString(4));
					ua.setCity(rs1.getString(5));
					ua.setPincode(rs1.getString(6));
					ua.setLangitude(rs1.getString(7));
					ua.setLatitude(rs1.getString(8));
					ua.setFavourite(rs1.getBoolean(9));
				
					al.add(ua);
				}
				ul.setAddressList(al);
				return null;							
			}							
		});
	    ul.setResponse("3");
		ul.setMessage("V-Mart Address Book Data");
		list.add(ul);
		return list;					
}

	@Override
	public List<VMartAddressList> update(VMartAddress vMartAddress) {		
		ArrayList<VMartAddressList> list = new ArrayList<>();
		VMartAddressList ual = new VMartAddressList();
		String update = "update vmartaddressbook set favourite=? where addressId=?";
		int count = jdbcTemplate.update(update,vMartAddress.getFavourite(),vMartAddress.getAddressId());
		if(count>0) {
			ual.setResponse("3");
			ual.setMessage("Your Address is updated successfully.");
			list.add(ual);
		}else {
			ual.setResponse("0");
			ual.setMessage("Your Address not updated.");
			list.add(ual);
		}				
		return list;					
}

	

}
