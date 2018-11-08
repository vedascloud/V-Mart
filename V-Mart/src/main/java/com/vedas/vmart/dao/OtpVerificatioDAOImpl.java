package com.vedas.vmart.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.vedas.vmart.model.Cart;
import com.vedas.vmart.model.CartList;
import com.vedas.vmart.model.DeliveryInfo;
import com.vedas.vmart.model.ItemsInfo;
import com.vedas.vmart.model.OrderInfo;
import com.vedas.vmart.model.OrdersList;
import com.vedas.vmart.model.OtpVerification;
import com.vedas.vmart.model.OtpVerifyList;


public class OtpVerificatioDAOImpl implements OtpVerificatioDAO {
	
	private JdbcTemplate jdbcTemplate;

	public OtpVerificatioDAOImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public List<OtpVerifyList> verify(OtpVerification otp) {
		
		String sql = "select otpnumber,cart_id from signup where mobileno=" +otp.getMobileNumber();
				
		return jdbcTemplate.query(sql, new ResultSetExtractor<List<OtpVerifyList>>() {

			@Override
			public List<OtpVerifyList> extractData(ResultSet rs) throws SQLException, DataAccessException {
				ArrayList<OtpVerifyList> list = new ArrayList<OtpVerifyList>();
				OtpVerifyList resmodel = new OtpVerifyList();
				if(rs.next()) {
					String otpdb = rs.getString(1);
					System.out.println("otp number from db.." +otpdb);
					if(otpdb.equals(otp.getOtpNumber())) {
						System.out.println("entered into if loop..");
						String update ="update signup set status =? where mobileno=?";
						System.out.println(update);
						jdbcTemplate.update(update,"1",otp.getMobileNumber());
						
						String join = "SELECT cart.cart_id,cart.pro_id,cart.mobile,products.itemname,products.url,cart.mrpPrice,cart.vmartPrice,cart.quantity,cart.netweight,products.sub_id,cart.timestamp"
								+ "     FROM products "
								+ "     INNER JOIN cart ON products.quantity=cart.netweight where products.pro_id=cart.pro_id and mobile=? order by timestamp desc";  //Here joined 2 tables and fetched the data.
						
						@SuppressWarnings("unused")
						List<CartList> cart = jdbcTemplate.query(join, new Object[] {otp.getMobileNumber()},new ResultSetExtractor<List<CartList>>() {

							@Override
							public List<CartList> extractData(ResultSet rs1) throws SQLException, DataAccessException {
								ArrayList<Cart> al = new ArrayList<Cart>();
								while(rs1.next()) {
									Cart cart = new Cart();
									cart.setCartId(rs1.getString(1));
									cart.setProductId(rs1.getString(2));
									cart.setMobileNumber(rs1.getString(3));
									cart.setItemName(rs1.getString(4));
									cart.setUrl(rs1.getString(5));
									cart.setMrpPrice(rs1.getString(6));
									cart.setVmartPrice(rs1.getString(7));
									cart.setQuantity(rs1.getString(8));
									cart.setNetWeight(rs1.getString(9));
									cart.setSubId(rs1.getString(10));
									cart.setTimeStamp(rs1.getString(11));
									al.add(cart);
								}
								resmodel.setCartData(al);
								return null;
							}
							
						});
						
						String orders = "select distinct orderid,userid,totalcost,deliverycharges,deliverytype,paymenttype,timestamp,name,mobile,address,pin,deliverydate,deliverytime from vmartorders where userid=?";
						@SuppressWarnings("unused")
						List<OrdersList> order = jdbcTemplate.query(orders, new Object[] {otp.getMobileNumber()},new ResultSetExtractor<List<OrdersList>>() {
							
							@Override
							public List<OrdersList> extractData(ResultSet rs) throws SQLException, DataAccessException {
								ArrayList<OrderInfo> o = new ArrayList<>();
								
								if(rs.next()) {
									OrderInfo oi = new OrderInfo();
									oi.setOrderId(rs.getString(1));
									oi.setUserId(rs.getString(2));
									oi.setTotalCoast(rs.getString(3));
									oi.setDeliveryCharges(rs.getString(4));
									oi.setDeliveryType(rs.getString(5));
									oi.setPaymentType(rs.getString(6));
									oi.setTimeStamp(rs.getString(7));
									
									System.out.println("userid..." +rs.getString(1));
									DeliveryInfo di = new DeliveryInfo();
									di.setName(rs.getString(8));
									di.setMobileno(rs.getString(9));
									di.setAddress(rs.getString(10));
									di.setPin(rs.getString(11));
									di.setDeliveryDate(rs.getString(12));
									di.setDeliveryTime(rs.getString(13));
									oi.setDeliveryInfo(di);
									String items = "select * from vmartorders where orderid = ?";
									jdbcTemplate.query(items, new Object[] {rs.getString(1)},new ResultSetExtractor<ItemsInfo>() {

										@Override
										public ItemsInfo extractData(ResultSet rs1) throws SQLException,
												DataAccessException {
											
												ArrayList<ItemsInfo> ii = new ArrayList<>();
												while(rs1.next()) {
													ItemsInfo ii1 = new ItemsInfo();
													ii1.setProductId(rs1.getString(14));
													ii1.setItemName(rs1.getString(15));
													ii1.setQauntity(rs1.getString(16));
													ii1.setPrice(rs1.getString(17));
													ii1.setNetWeight(rs1.getString(18));
													ii.add(ii1);
													
												}
												oi.setItemsInfo(ii);
												return null;
											
										}
										
									});
									o.add(oi);
									
								}
								resmodel.setOrderInfo(o);
								return null;
								
							}
							
						});
						
						resmodel.setCartId(rs.getString(2));
		        		resmodel.setResponse("3");
			        	resmodel.setMessage("user verified successfully");
			        	list.add(resmodel);
					}else {
						
						resmodel.setResponse("1");
			        	resmodel.setMessage("entered otp is wrong");
			        	list.add(resmodel);
					}
					
					
				}
				return list;
				
				
		
			}
		});
		
		
	}

}
