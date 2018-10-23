package com.vedas.vmart.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.vedas.vmart.model.Category;
import com.vedas.vmart.model.CategoryList;
import com.vedas.vmart.model.Subcategory;

public class CategoryListDAOImpl implements CategoryListDAO {
	
private JdbcTemplate jdbcTemplate;
	
	public CategoryListDAOImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
		
	@Override
	public List<CategoryList> list() {
		// TODO Auto-generated method stub
		
		String sql = "select * from categories";
		List<CategoryList> listContact = jdbcTemplate.query(sql, new ResultSetExtractor<List<CategoryList>>() {

			@Override
			public List<CategoryList> extractData(ResultSet rs) throws SQLException, DataAccessException {
				// TODO Auto-generated method stub
				ArrayList<CategoryList> list=new ArrayList<CategoryList>();  
				ArrayList<Category> cat = new ArrayList<Category>();
				CategoryList ctg = new CategoryList();
				
		        while(rs.next()){  
		        Category e=new Category();  
		        e.setCategoryName(rs.getString(2));
		        
		        String sql2 = "SELECT * FROM sub_categories WHERE cat_id=" + rs.getString(1);
		        
				jdbcTemplate.query(sql2, new ResultSetExtractor<Subcategory>() {

					@Override
					public Subcategory extractData(ResultSet rs1) throws SQLException,
							DataAccessException {
						ArrayList<Subcategory> subcat = new ArrayList<Subcategory>();
					
						while (rs1.next()) {
							Subcategory contact = new Subcategory();
							contact.setSubCategoryId(rs1.getString(1));
							contact.setSubCategoryName(rs1.getString(2));
							subcat.add(contact);
						}
						e.setSubcategories(subcat);
											
						return null;
					}
					
				});
				cat.add(e);
		        ctg.setCategories(cat);
		        }
		        ctg.setResponse("3");
		        ctg.setMessage("fetch successfully");
		        list.add(ctg);
				return list;
			}
			
		});
		
		return listContact;
		
	}

}
