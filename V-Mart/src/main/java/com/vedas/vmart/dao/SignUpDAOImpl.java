package com.vedas.vmart.dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.vedas.vmart.model.SignUp;

public class SignUpDAOImpl implements SignUpDAO{
	
	private JdbcTemplate jdbcTemplate;

	public SignUpDAOImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<SignUp> signup(String mobileNumber) {
					
					String otp = String.valueOf((int)(((Math.random())*1000)+1990));
					String authkey = "242109AML1EVYdNm5bbdcc33";
		            String mobiles = mobileNumber;
		            String senderId = "IVMART";
		            String message = "Your verification code is."+otp;
		            //String text = "Thank you for choosing vmart";
		            String route="4";
		            URLConnection myURLConnection=null;
		            URL myURL=null;
		            BufferedReader reader=null;
		            @SuppressWarnings("deprecation")
					String encoded_message=URLEncoder.encode(message);
		            String mainUrl="http://api.msg91.com/api/sendhttp.php?";
		            StringBuilder sbPostData= new StringBuilder(mainUrl);
		            sbPostData.append("authkey="+authkey);
		            sbPostData.append("&mobiles="+mobiles);
		            sbPostData.append("&message="+encoded_message);
		            sbPostData.append("&route="+route);
		            sbPostData.append("&sender="+senderId);

		            mainUrl = sbPostData.toString();
		            try
		            {
		                myURL = new URL(mainUrl);
		                myURLConnection = myURL.openConnection();
		                myURLConnection.connect();
		                reader= new BufferedReader(new InputStreamReader(myURLConnection.getInputStream()));
		                String response;
		                if ((response = reader.readLine()) != null) 
		                          System.out.println(response);
		                reader.close();	
		                		
		                		String select = "SELECT mobileno from signup where mobileno=" +mobileNumber;
		                		return jdbcTemplate.query(select, new ResultSetExtractor<List<SignUp>>() {
		                			public List<SignUp> extractData(ResultSet r) throws SQLException, DataAccessException{
		                				ArrayList<SignUp> list = new ArrayList<SignUp>();
		                				SignUp sign = new SignUp();
		                				if(r.next()) {
		                					
		                					String update = "update signup set messageid=?, otpnumber=?,status=? where mobileno=?";
		                					System.out.println("final query.."+update);
		                					int count =jdbcTemplate.update(update,response,otp,"0",mobileNumber);
		                					if(count>0) {
		                						sign.setResponse("3");
			                					sign.setMessage("otp updated success");
			                					list.add(sign);
		                					}else {
		                						sign.setResponse("0");
			                					sign.setMessage("otp updated failed");
			                					list.add(sign);
		                						
		                					}
		                					
									
		                			}else {
		                				
		                			  String id = "cart_"+String.valueOf((int)(((Math.random())*1000)+1990));
		                        	  String sql2 = "Insert into signup (mobileno,messageid,otpnumber,status,cart_id) values (?,?,?,?,?)";
		                        	  
		                        	  int count = jdbcTemplate.update(sql2, mobileNumber,response,otp,"0",id);
		                        	  if(count>0) {
		                        		    sign.setCatId(id);
	                        	  			sign.setResponse("3");
	    		        					sign.setMessage("OTP successfully sent to your mobileno ");
	    		        					list.add(sign);
		                        		  
		                        	  }else {
		                        		  	sign.setResponse("0");
	    		        					sign.setMessage("please pass correct data");
	    		        					list.add(sign);
		                        	  }
		                        	  			
		                			}
		                		return list;	
		                		}
	                });
		            }
		            
		            catch (IOException e)
		            {
		                    e.printStackTrace();
		            }
	
		            
					return null;
	}

	
	
}
