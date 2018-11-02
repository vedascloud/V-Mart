package com.vedas.vmart.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.vedas.vmart.model.UserAddress;
import com.vedas.vmart.model.UserAddressList;

public class UserAddressDAOImpl  implements UserAddressDAO{
	
	private JdbcTemplate jdbcTemplate;

	public UserAddressDAOImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	//------------Add UserAddress----------------
		@Override
		public List<UserAddressList> insert(UserAddress userAddress) {		
			String select = "select * from signup where  mobileno=? ";				
			return jdbcTemplate.query(select, new Object[] { userAddress.getUserId()},new ResultSetExtractor<List<UserAddressList>>() {
				@Override
				public List<UserAddressList> extractData(ResultSet rs) throws SQLException, DataAccessException {
					ArrayList<UserAddressList> list=new ArrayList<UserAddressList>();
					UserAddressList ual = new UserAddressList();
					if(rs.next()) {					
						
						String Id = "useradd_"+String.valueOf((int)(((Math.random())*1000)+1990));						
						String insert = "INSERT INTO useraddressbook (addressId,name,address,landmark,pinCode,area,city,state,mobile,favourite,userId)"+" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
						int count =jdbcTemplate.update(insert,Id,userAddress.getName(),userAddress.getAddress(),userAddress.getLandmark(),userAddress.getPinCode(),userAddress.getArea(),userAddress.getCity(),userAddress.getState(),userAddress.getMobile(),userAddress.getFavourite(),userAddress.getUserId());
						if(count>0) {
							ual.setAddressId(Id);
							ual.setMessage("Address Added...");
							ual.setResponse("3");
							list.add(ual);							
						}else {							
							ual.setMessage("Check the data...");
							ual.setResponse("0");
							list.add(ual);
						}										
					}						
					return list;					
				}			
			});		
		}
	
	//-----------------Update UserAddressBook---------------------
		@Override
		public List<UserAddressList> update(UserAddress userAddress) {		
					ArrayList<UserAddressList> list = new ArrayList<>();
					UserAddressList ual = new UserAddressList();
					String update = "update useraddressbook set  name=?, address=?, landmark=?, pinCode=?, area=?, city=?, state=?, mobile=?, favourite=? where addressId=?";
					int count = jdbcTemplate.update(update,userAddress.getName(),userAddress.getAddress(),userAddress.getLandmark(),userAddress.getPinCode(),userAddress.getArea(),userAddress.getCity(),userAddress.getState(),userAddress.getMobile(),userAddress.getFavourite(),userAddress.getAddressId());
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
	
	//-----------------Select UserAddress--------------------
		
		@Override
		public List<UserAddressList> select(String mobile) {		
						ArrayList<UserAddressList>	list = new ArrayList<>();
						UserAddressList ul = new UserAddressList(); 
						String join = "select * from useraddressbook where userId = "+mobile ;							
						@SuppressWarnings("unused")
						List<UserAddressList> cart = jdbcTemplate.query(join,new ResultSetExtractor<List<UserAddressList>>() {
							@Override
							public List<UserAddressList> extractData(ResultSet rs1) throws SQLException, DataAccessException {
															
								ArrayList<UserAddress> al = new ArrayList<UserAddress>();
								while(rs1.next()) {
									UserAddress ua = new UserAddress();
									ua.setAddressId(rs1.getString(1));
									ua.setName(rs1.getString(2));
									ua.setAddress(rs1.getString(3));
									ua.setLandmark(rs1.getString(4));
									ua.setPinCode(rs1.getString(5));
									ua.setArea(rs1.getString(6));
									ua.setCity(rs1.getString(7));
									ua.setState(rs1.getString(8));
									ua.setMobile(rs1.getString(9));
									ua.setFavourite(rs1.getBoolean(10));
									ua.setUserId(rs1.getString(11));
								
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
	
	//--------------Delete UserAddress------------------
		@Override
		public List<UserAddressList> delete(UserAddress userAddress) {		
			ArrayList<UserAddressList> list = new ArrayList<>();
			UserAddressList ual = new UserAddressList();
			String delete = "delete from useraddressbook where addressId=?";
			int count = jdbcTemplate.update(delete,userAddress.getAddressId());
			if(count>0) {
				ual.setResponse("3");
				ual.setMessage("Your address is removed from addressbook successfully.");
				list.add(ual);
			}else {
				ual.setResponse("0");
				ual.setMessage("Your address is not removed.");
				list.add(ual);
			}		
			return list;
		}	
	
}
