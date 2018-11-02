package com.vedas.vmart.dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.vedas.vmart.model.OrdersList;

public class OrdersListDAOImpl implements OrdersListDAO {
	
private JdbcTemplate jdbcTemplate;
	
	public OrdersListDAOImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<OrdersList> insert(String json) {
		
				
		String fetch = "select count(orderid)+1 from vmartorders";
		List<OrdersList> listContact = jdbcTemplate.query(fetch, new ResultSetExtractor<List<OrdersList>>() {
			int value = 0;
			String itemname=null;String productid=null;	String quantity=null;String price=null;String weight=null;
			String userid=null;String totalcost=null;String deliverytype=null;String deliverycharges=null;String paymenttype=null;String timestamp=null;
			String name=null;String mobile=null;String address=null;String pin=null;String date=null;String time=null;
			JSONArray item=null;JSONObject delivery=null;
			@Override
			public List<OrdersList> extractData(ResultSet rs) throws SQLException, DataAccessException {
				ArrayList<OrdersList> list = new ArrayList<>();
				OrdersList ol = new OrdersList();
               if(rs.next()) {
					
					value = Integer.parseInt(rs.getString(1)); 
					
				}
				try {
					
					JSONObject obj = new JSONObject(json); //splitting request body into jsonobject.
					userid=obj.getString("userId");
					totalcost=obj.getString("totalCost");
					deliverycharges=obj.getString("deliveryCharges");
					deliverytype=obj.getString("deliveryType");
					paymenttype=obj.getString("paymentType");
					timestamp=obj.getString("timeStamp");
					
					try {
						delivery=obj.getJSONObject("deliveryInfo");
						name = delivery.getString("name");
						mobile = delivery.getString("mobile");
						address = delivery.getString("address");
						pin = delivery.getString("pin");
						date = delivery.getString("date");
						time = delivery.getString("time");
												
						
					}catch(Exception e) {
						e.printStackTrace();
					}
					
					int count = 0;
					String orderid = "Order_"+new SimpleDateFormat("ddMMyyy").format(new Date())+value;
					try {
						item=obj.getJSONArray("itemsInfo");
						System.out.println("array length..." +item.length());
						final int n = item.length();
						for(int i=0; i<n; ++i) {
							
							JSONObject j=null; j = item.getJSONObject(i);
							productid = j.getString("productId");
							itemname = j.getString("itemName");
							quantity = j.getString("quantity");
							price = j.getString("price");
							weight = j.getString("netWeight");
							
							
					 count = jdbcTemplate.update("insert into vmartorders(orderid,userid,totalcost,deliverycharges,deliverytype,paymenttype,timestamp,name,mobile,address,pin,deliverydate,deliverytime,productid,itemname,quantity,price,netweight) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"
							, new Object[] {
									orderid,userid,totalcost,deliverycharges,deliverytype,paymenttype,timestamp,name,mobile,address,pin,date,time,productid,itemname,quantity,price,weight
							
							});
						}
						String authkey = "242109AML1EVYdNm5bbdcc33";
						String mobiles = userid;
			            String senderId = "IVMART";
			            String message = "Your order is placed successfully."+"\n"		            		 
			            		+"Your cart value is. " +totalcost+"\n"
			            		+"Order reference id: " +orderid+"\n"
			            		+"Delivery type: " +deliverytype+"\n"
			            		+"Delivery Date: " +date +","+time+"\n"
			            		+"Thank You";
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
			                
			            }
			            
			            catch (IOException e)
			            {
			                    e.printStackTrace();
			            }
						if(count>0)	{
							ol.setOrderId(orderid);
							ol.setResponse("3");
							ol.setMessage("orders inserted");
							list.add(ol);
							String delete = "delete from cart where mobile=?";
							int d = jdbcTemplate.update(delete,userid);
						}else {
							ol.setResponse("0");
							ol.setMessage("orders inserted failed");
							list.add(ol);
						}
						
							
				}catch(Exception e) {
					e.printStackTrace();
				}
			
			}catch(Exception e) {
				e.printStackTrace();
			}
				
				return list;
			}
						
		});
		
		return listContact;
	}

}
