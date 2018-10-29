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

public class CartDAOImpl implements CartDAO{
	
private JdbcTemplate jdbcTemplate;
	
	public CartDAOImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<CartList> addcart(Cart cart) {
		
					
			String query = "select * from signup where mobileno= ?" ;
			
			List<CartList> listContact = jdbcTemplate.query(query, new Object[] {cart.getMobileNumber()}, new ResultSetExtractor<List<CartList>>() {

				@Override
				public List<CartList> extractData(ResultSet rs) throws SQLException, DataAccessException {
					ArrayList<CartList> list = new ArrayList<>();
					CartList cl = new CartList();
					if(rs.next()) {
						
						System.out.println("cart id..." +cart.getCartId());
						int count = jdbcTemplate.update(
							    "INSERT INTO cart(cart_id,pro_id,mobile,itemName,mrpPrice,vmartPrice,quantity,netweight,timestamp)VALUES(?,?,?,?,?,?,?,?,?)", new Object[] {
							      cart.getCartId(),cart.getProductId(),cart.getMobileNumber(),cart.getItemName(),cart.getMrpPrice(),cart.getVmartPrice(),cart.getQuantity(),cart.getNetWeight(),cart.getTimeStamp()
							        });
						if(count>0) {
							cl.setResponse("3");
						    cl.setMessage("Cart data inserted");
						    list.add(cl);
						}else {
							cl.setResponse("1");
						    cl.setMessage("check the data");
						    list.add(cl);
						}
					}
						
					
					return list;
					
				}
				
			});
			return listContact;
			
	}

	@Override
	public List<CartList> update(Cart cart) {
		
				ArrayList<CartList> list = new ArrayList<>();
				CartList cl = new CartList();
				String update = "update cart set mrpPrice=?,vmartPrice=?,quantity=? where netweight=? and pro_id=? and mobile=?";
				int count = jdbcTemplate.update(update,cart.getMrpPrice(),cart.getVmartPrice(),cart.getQuantity(),cart.getNetWeight(),cart.getProductId(),cart.getMobileNumber());
				if(count>0) {
					cl.setResponse("3");
					cl.setMessage("Your cart is updated successfully.");
					list.add(cl);
				}else {
					cl.setResponse("0");
					cl.setMessage("Your cart not updated.");
					list.add(cl);
				}
				
				return list;
					
	}

	@Override
	public List<CartList> delete(Cart cart) {
		
		ArrayList<CartList> list = new ArrayList<>();
		CartList cl = new CartList();
		String delete = "delete from cart where netweight=? and pro_id=? and mobile=?";
		int count = jdbcTemplate.update(delete,cart.getNetWeight(),cart.getProductId(),cart.getMobileNumber());
		if(count>0) {
			cl.setResponse("3");
			cl.setMessage("Your product is removed from cart successfully.");
			list.add(cl);
		}else {
			cl.setResponse("0");
			cl.setMessage("Your product is not removed.");
			list.add(cl);
		}
		
		return list;
		
	}

	@Override
	public List<CartList> getcart(String mobile) {
		
					ArrayList<CartList> list = new ArrayList<>();
					CartList cl = new CartList();
					String join = "SELECT cart.cart_id,cart.pro_id,cart.mobile,products.itemname,products.url,cart.mrpPrice,cart.vmartPrice,cart.quantity,cart.netweight,products.sub_id,cart.timestamp"
							+ "     FROM products "
							+ "     INNER JOIN cart ON products.quantity=cart.netweight where products.pro_id=cart.pro_id and mobile=? order by timestamp desc";  //Here joined 2 tables and fetched the data.
					
					@SuppressWarnings("unused")
					List<CartList> cart = jdbcTemplate.query(join, new Object[] {mobile},new ResultSetExtractor<List<CartList>>() {

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
							cl.setCartList(al);
							return null;
						}
						
					});
					
				
				cl.setResponse("3");
				cl.setMessage("your cart data");
				list.add(cl);
				return list;
					
	
	}

	@Override
	public List<CartList> deleteallcartitems(String mobile) {
		
					ArrayList<CartList> list = new ArrayList<>();
					CartList cl = new CartList();
					
					String delete = "delete from cart where mobile=?";
					int count = jdbcTemplate.update(delete,mobile);
					if(count>0) {
						cl.setResponse("3");
						cl.setMessage("cart data deleted");
						list.add(cl);
					}else {
						cl.setResponse("0");
						cl.setMessage("cart data not deleted");
						list.add(cl);
					}
				
				
				
				return list;
		
							
		
	}

}
