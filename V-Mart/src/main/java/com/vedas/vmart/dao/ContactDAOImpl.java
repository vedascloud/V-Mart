package com.vedas.vmart.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import com.vedas.vmart.model.Contact;

/**
 * An implementation of the ContactDAO interface.
 * @author www.codejava.net
 *
 */

public class ContactDAOImpl implements ContactDAO {

	private JdbcTemplate jdbcTemplate;
	
	public ContactDAOImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public void saveOrUpdate(Contact contact) {
		if (contact.getId() > 0) {
			// update
			String sql = "UPDATE contact SET name=?, age=?, salary=?, "
						+ " WHERE id=?";
			jdbcTemplate.update(sql, contact.getName(), contact.getAge(),
					contact.getSalary(), contact.getId());
		} else {
			// insert
			String sql = "INSERT INTO contact (name, age, salary)"
						+ " VALUES (?, ?, ?)";
			jdbcTemplate.update(sql, contact.getName(), contact.getAge(),
					contact.getSalary());
		}
		
	}

	@Override
	public void delete(int contactId) {
		String sql = "DELETE FROM contact WHERE id=?";
		jdbcTemplate.update(sql, contactId);
	}

	@Override
	public List<Contact> list() {
		String sql = "SELECT * FROM contact";
		List<Contact> listContact = jdbcTemplate.query(sql, new RowMapper<Contact>() {

			@Override
			public Contact mapRow(ResultSet rs, int rowNum) throws SQLException {
				Contact aContact = new Contact();
	
				aContact.setId(rs.getInt("id"));
				aContact.setName(rs.getString("name"));
				aContact.setAge(rs.getInt("age"));
				aContact.setSalary(rs.getInt("salary"));
				
				return aContact;
			}
			
		});
		
		return listContact;
	}

	@Override
	public Contact get(int contactId) {
		String sql = "SELECT * FROM contact WHERE id=" + contactId;
		return jdbcTemplate.query(sql, new ResultSetExtractor<Contact>() {

			@Override
			public Contact extractData(ResultSet rs) throws SQLException,
					DataAccessException {
				if (rs.next()) {
					Contact contact = new Contact();
					contact.setId(rs.getInt("id"));
					contact.setName(rs.getString("name"));
					contact.setAge(rs.getInt("age"));
					contact.setSalary(rs.getInt("salary"));
					
					return contact;
				}
				
				return null;
			}
			
		});
	}

}
