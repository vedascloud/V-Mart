package com.vedas.vmart.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import com.vedas.vmart.model.OtpVerification;
import com.vedas.vmart.model.OtpVerifyList;

public class OtpVerificatioDAOImpl implements OtpVerificatioDAO {
	
	private JdbcTemplate jdbcTemplate;

	public OtpVerificatioDAOImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public List<OtpVerifyList> verify(OtpVerification otp) {
		
		String sql = "select otpnumber from signup where mobileno=" +otp.getMobileNumber();
				
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
