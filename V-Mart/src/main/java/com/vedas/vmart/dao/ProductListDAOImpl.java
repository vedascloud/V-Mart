package com.vedas.vmart.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.vedas.vmart.model.Pinfo;
import com.vedas.vmart.model.Product;
import com.vedas.vmart.model.ProductList;

public class ProductListDAOImpl implements ProductListDAO{
	
private JdbcTemplate jdbcTemplate;
	
	public ProductListDAOImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<ProductList> list(String id) {
		// TODO Auto-generated method stub
	    System.out.println("subcategory id...."+id);
		String sql = "select distinct itemname,pro_id,description from products where sub_id = "+id;
		
		List<ProductList> listContact = jdbcTemplate.query(sql, new ResultSetExtractor<List<ProductList>>() {

			@Override
			public List<ProductList> extractData(ResultSet rs) throws SQLException, DataAccessException {
				// TODO Auto-generated method stub
				ArrayList<ProductList> list=new ArrayList<ProductList>();  
				ArrayList<Product> cat = new ArrayList<Product>();
				ProductList ctg = new ProductList();
				
		        while(rs.next()){  
		        	
		        Product e=new Product();  
		        e.setItemName(rs.getString(1));
		        e.setDescription(rs.getString(3));
		        e.setProductId(rs.getString(2));
		        System.out.println("product info"+rs.getString(2));
		        
		        String sql2 = "SELECT * FROM products WHERE pro_id=" + rs.getString(2);
		        
				jdbcTemplate.query(sql2, new ResultSetExtractor<Pinfo>() {

					@Override
					public Pinfo extractData(ResultSet rs1) throws SQLException,
							DataAccessException {
						ArrayList<Pinfo> plist=new ArrayList<Pinfo>();
				        
						while (rs1.next()) {
							Pinfo contact = new Pinfo();
							contact.setUrl(rs1.getString(2));
							System.out.println("sub name from db.."+rs1.getString(2));
							contact.setMrp(rs1.getString(4));
							contact.setVmartPrice(rs1.getString(5));
							contact.setQuantity(rs1.getString(6));
							plist.add(contact);
						}
						e.setPInfo(plist);
						
						System.out.println("sub cat length..."+plist.size());
						
						return null;
					}
					
				});
				cat.add(e);
		        }
		        ctg.setProducts(cat);
		        
		        ctg.setResponse("3");
		        ctg.setMessage("fetch successfully");
		        list.add(ctg);
		        
		        return list;
			}
			
		});
	
		return listContact;
		
	}

	@Override
	public List<ProductList> getall() {

		String sql = "select distinct itemname,pro_id,description from products";
		
		List<ProductList> listContact = jdbcTemplate.query(sql, new ResultSetExtractor<List<ProductList>>() {

			@Override
			public List<ProductList> extractData(ResultSet rs) throws SQLException, DataAccessException {
				// TODO Auto-generated method stub
				ArrayList<ProductList> list=new ArrayList<ProductList>();  
				ArrayList<Product> cat = new ArrayList<Product>();
				ProductList ctg = new ProductList();
				
		        while(rs.next()){  
		        	
		        Product e=new Product();  
		        e.setItemName(rs.getString(1));
		        e.setDescription(rs.getString(3));
		        e.setProductId(rs.getString(2));
		       		        
		        String sql2 = "SELECT * FROM products WHERE pro_id=" + rs.getString(2);
		        
				jdbcTemplate.query(sql2, new ResultSetExtractor<Pinfo>() {

					@Override
					public Pinfo extractData(ResultSet rs1) throws SQLException,
							DataAccessException {
						ArrayList<Pinfo> plist=new ArrayList<Pinfo>();
				        
						while (rs1.next()) {
							Pinfo contact = new Pinfo();
							contact.setUrl(rs1.getString(2));
							contact.setMrp(rs1.getString(4));
							contact.setVmartPrice(rs1.getString(5));
							contact.setQuantity(rs1.getString(6));
							plist.add(contact);
						}
						e.setPInfo(plist);
											
						return null;
					}
					
				});
				cat.add(e);
		        }
		        ctg.setProducts(cat);
		        
		        ctg.setResponse("3");
		        ctg.setMessage("fetch successfully");
		        list.add(ctg);
		        
		        return list;
			}
			
		});
	
		return listContact;
		
	}

}
