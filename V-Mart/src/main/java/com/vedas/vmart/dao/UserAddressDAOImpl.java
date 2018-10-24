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

	//------------Add UserAdd----------------
	@Override
	public List<UserAddressList> insert(UserAddress userAddress) {
		
		String sql = "select * from useraddressbook where  addressId =?";
				
		return jdbcTemplate.query(sql, new Object[] {userAddress.getAddressId()},new ResultSetExtractor<List<UserAddressList>>() {

			@Override
			public List<UserAddressList> extractData(ResultSet rs) throws SQLException, DataAccessException {
				ArrayList<UserAddressList> list=new ArrayList<UserAddressList>();
				UserAddressList uab = new UserAddressList();
				if(rs.next()) {
					
		            try
		            {										
						String sql4 = "update useraddressbook set  name=?, address=?, landmark=?, pinCode=?, area=?, city=?, state=?, mobile=?, fevourite=? where addressId=? and userId=? ";
						jdbcTemplate.update(sql4,userAddress.getName(),userAddress.getAddress(),userAddress.getLandmark(),userAddress.getPinCode(),userAddress.getArea(),userAddress.getCity(),userAddress.getState(),userAddress.getMobile(),"1",userAddress.getAddressId(),userAddress.getUserId());
						
						uab.setMessage("Address Updated...");
						uab.setResponse("1");
						
						list.add(uab);
					}
		            catch (Exception e)
		            {
		                    e.printStackTrace();
		            }
		            
				}else {
					String sql5 = "INSERT INTO useraddressbook (addressId,name,address,landmark,pinCode,area,city,state,mobile,fevourite,userId)"+" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
					jdbcTemplate.update(sql5,userAddress.getAddressId(),userAddress.getName(),userAddress.getAddress(),userAddress.getLandmark(),userAddress.getPinCode(),userAddress.getArea(),userAddress.getCity(),userAddress.getState(),userAddress.getMobile(),"0",userAddress.getUserId());
				
					uab.setMessage("Address Added...");
					uab.setResponse("3");
					
					list.add(uab);
					
				}
			
				return list;	
				
			}
			
		});		
	
	}
	
	//-----------------Select UserAdd--------------------

	@Override
	public List<UserAddressList> select(UserAddress userAddress) {
		
		String sql = "select * from useraddressbook ";
				
		return jdbcTemplate.query(sql, new ResultSetExtractor<List<UserAddressList>>() {

			@Override
			public List<UserAddressList> extractData(ResultSet rs) throws SQLException, DataAccessException {
				ArrayList<UserAddressList> list=new ArrayList<UserAddressList>();
				UserAddress ua = new UserAddress();
				while(rs.next()) {				
		           
						ua.setAddressId(rs.getString(1));
						ua.setName(rs.getString(2));
						ua.setAddress(rs.getString(3));
						ua.setLandmark(rs.getString(4));
						ua.setPinCode(rs.getString(5));
						ua.setArea(rs.getString(6));
						ua.setCity(rs.getString(7));
						ua.setState(rs.getString(8));
						ua.setMobile(rs.getString(9));
						
						//list.add();
						
						}
			
				return list;	
				
			}
			
		});		
		
	}

	@Override
	public List<UserAddressList> list() {
		// TODO Auto-generated method stub
		
		String sql = "select * from useraddressbook";
		List<UserAddressList> listContact = jdbcTemplate.query(sql, new ResultSetExtractor<List<UserAddressList>>() {

			@Override
			public List<UserAddressList> extractData(ResultSet rs) throws SQLException, DataAccessException {
				// TODO Auto-generated method stub
				ArrayList<UserAddressList> list=new ArrayList<UserAddressList>();  
				ArrayList<UserAddress> cat = new ArrayList<UserAddress>();
				UserAddressList ctg = new UserAddressList();
				
		        while(rs.next()){  
		        	UserAddress e=new UserAddress();
		        	e.setAddressId(rs.getString(1));
		        	e.setMobile(rs.getString(9));
		        	
		        	cat.add(e); 	
		        	
		        }
		        
		        
		        ctg.setResponse("3");
		        ctg.setMessage("fetch successfully");
		        list.add(ctg);
				return list;
			}
			
		});
		
		return listContact;
		
	}
	
	
	

	
	
		
		/*
		
		String sql = "select * from useraddressbook";
		List<UserAddressList> listContact = jdbcTemplate.query(sql, new ResultSetExtractor<List<UserAddressList>>() {

			@Override
			public List<UserAddressList> extractData(ResultSet rs) throws SQLException, DataAccessException {
				ArrayList<UserAddressList> list = new ArrayList<UserAddressList>();
				ArrayList<UserAddress> cat =new ArrayList<UserAddress>();  
				
				UserAddressList ctg = new UserAddressList();
				
		        while(rs.next()){
		        	UserAddress pl = new UserAddress();
		        	pl.setAddressId(rs.getString(1));
		        	pl.setName(rs.getString(2));
		        	pl.setAddress(rs.getString(3));
		        	pl.setLandmark(rs.getString(4));
		        	pl.setPinCode(rs.getString(5));
		        	pl.setArea(rs.getString(6));
		        	pl.setCity(rs.getString(7));
		        	pl.setState(rs.getString(8));
		        	pl.setMobile(rs.getString(9));
		        	cat.add(pl);
		        	
		        	
		        }
		        ctg.setProductsList(cat);
		        ctg.setResponse("3");
		        ctg.setMessage("fetched products lists");
		        list.add(ctg);
		        return list;
		        
			}
			
		});
		// TODO Auto-generated method stub
		return listContact;
	*/
		
	
	
	
	

}
