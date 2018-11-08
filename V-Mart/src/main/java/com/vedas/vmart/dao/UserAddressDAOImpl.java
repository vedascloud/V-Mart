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

	//-----------------------------------Add UserAddress-----------------------------------------------
	//Here List<> (is a child Interface of Collection) is used for list-of all the parameters of a model class and
	//insert() is a method to perform operation by using parameter of that method
	//UserAddress is a model class 
	//userAddress is a instance of the UserAddress 
		@Override
		public List<UserAddressList> insert(UserAddress userAddress) {		
			//Query is used for checking the given mobileno is available in SignUp table
			String select = "select * from signup where  mobileno=? ";
			//Query given SQL to create a prepared statement from SQL and a listof arguments to bind to the query, reading the ResultSet with aResultSetExtractor.
			//Here select is a SQL query, Object[] is a Object Array used to get variables of that Model Class Instance
			//ResultSetExtractor interface is a callback interface used by JdbcTemplate's query methods
			return jdbcTemplate.query(select, new Object[] { userAddress.getUserId()},new ResultSetExtractor<List<UserAddressList>>() {
				//Here List<> is acting as an Interface
				//Implementations must implement this(extractData(ResultSet rs)) method to process the entire ResultSet
				@Override
				public List<UserAddressList> extractData(ResultSet rs) throws SQLException, DataAccessException {
					//Java ArrayList class uses a dynamic array for storing the elements. 
					//It inherits AbstractList class and implements List interface.
					//ArrayList is better for manipulations.
					//list is a instance of the ArrayList<UserAddressList> Class.
					ArrayList<UserAddressList> list=new ArrayList<UserAddressList>();
					//ual is a instance of the UserAddressList Class.
					UserAddressList ual = new UserAddressList();
					//Here If condition is used to check the condition of the ResultSet rs based on the SQL query
					if(rs.next()) {					
						
						String Id = "useradd_"+String.valueOf((int)(((Math.random())*1000)+1990));
						//insert is having String Query for Insert operation 
						String insert = "INSERT INTO useraddressbook (addressId,name,address,landmark,pinCode,area,city,state,mobile,favourite,userId)"+" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
						//Here count is taken as a one of the int data type, it is having jdbcTemplate.update() method
						//update() method is having SQL query and arguements of the Object
						int count = jdbcTemplate.update(insert,Id,userAddress.getName(),userAddress.getAddress(),userAddress.getLandmark(),userAddress.getPinCode(),userAddress.getArea(),userAddress.getCity(),userAddress.getState(),userAddress.getMobile(),userAddress.getFavourite(),userAddress.getUserId());
						//Here If Condition is used for checking the count value, count>0 means the update() method is Successfully executed
						if(count>0) {
							//ual is the instance of the UserAddressList Class, here we can applying Setter methods on available parameters
							//Setter methods are used for Set the values of available parameters
							ual.setAddressId(Id);
							ual.setMessage("Address Added...");
							ual.setResponse("3");
							//After completion of the setting all the parameter values, add the total instance to that list instance/object  by using add() method.
							list.add(ual);							
						}
						//Else part is used for while the Applied Condition(If) is failed situation time.
						else {				
							//ual is the instance of the UserAddressList Class, here we can applying Setter methods on available parameters
							//Setter methods are used for Set the values of available parameters
							ual.setMessage("Check the data...");
							ual.setResponse("0");
							//After completion of the setting all the parameter values, add the total instance to that list instance/object  by using add() method.
							list.add(ual);
						}										
					}	
					//return is used for returns the Object value.
					return list;					
				}			
			});		
		}
	
	//-----------------Update UserAddressBook---------------------
		@Override
		public List<UserAddressList> update(UserAddress userAddress) {		
			//Java ArrayList supports dynamic arrays that can grow as needed.
			//Java ArrayList class uses a dynamic array for storing the elements
			//It can contain duplicate element,it maintains insertion order,it is non synchronized,
			//it allows random access because array works at the index basis and it is good for manipulation .
					ArrayList<UserAddressList> list = new ArrayList<>();     //to print the response in array
					//ual is an Instance of the UserAddressList Class, it is used for setting the available variables values of that Class individually.
					UserAddressList ual = new UserAddressList();  //used to set the values of available variables of that Class. 
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
						//Creates an Object/Instance of an ArrayList<UserAddressList> class, list is an Instance of this Class
						ArrayList<UserAddressList>	list = new ArrayList<>();  //Used to listof all the data in a Single Array
						//Creates an Object/Instance of an UserAddressList Class
						UserAddressList ul = new UserAddressList();  //Used to set variable values individually.
						String fetch = "select * from useraddressbook where userId = "+mobile ;							
						@SuppressWarnings("unused")
						List<UserAddressList> data = jdbcTemplate.query(fetch,new ResultSetExtractor<List<UserAddressList>>() {
							@Override
							public List<UserAddressList> extractData(ResultSet rs1) throws SQLException, DataAccessException {
								//Creates an Object/Instance of an ArrayList<UserAddress> class, al is an Instance of this Class
								ArrayList<UserAddress> al = new ArrayList<UserAddress>();//used to set all the data in Array
								//While is Conditional Statement, it is used for checking the ResultSet values by using next() method
								//The condition is executes untill While Condition fails
								while(rs1.next()) {
									//Creates an Object/Instance of the UserAddress Class
									UserAddress ua = new UserAddress();//Used to set all available variables using that Instance, before that get the elements of available variables based on ResultSet.
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
									//After completion of all the variables values are setting by using Setter() methods, add the entire Object to al (Is an Instance of ArrayList<UserAddress>)
									al.add(ua);
								}
								ul.setAddressList(al);//used to set the AddressList<UserAddress> based on UserAddress 
								return null;							
							}							
						});
						//set the available variables values to the instance of the ul (is the instance of UserAddressList)
					    ul.setResponse("3");
						ul.setMessage("Your Address Book Data");
						//add the all setter values of ul(is the instance of UserAddressList) to list(is the instance of ArrayList<UserAddressList>)
						list.add(ul);
						return list;	//used to return the listof UserAddresses				
		}		
	
	//--------------Delete UserAddress------------------
		@Override
		public List<UserAddressList> delete(UserAddress userAddress) {	
			//Creates an Object/Instance of an ArrayList<UserAddressList> class, list is an Instance of this Class
			ArrayList<UserAddressList> list = new ArrayList<>();  //Used to listof all the data in a Single Array
			//Creates an Instance of the UserAddressList Class, ual is acts as a Instance of the Class
			UserAddressList ual = new UserAddressList();  //Used to Set available variable values to that Instance
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
