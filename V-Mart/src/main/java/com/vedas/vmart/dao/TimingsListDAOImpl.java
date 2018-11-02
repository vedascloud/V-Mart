package com.vedas.vmart.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.vedas.vmart.model.TimeSlot;
import com.vedas.vmart.model.Timings;
import com.vedas.vmart.model.TimingsList;

public class TimingsListDAOImpl implements TimingsListDAO {	 		 //Here implementation happens for all methods from TimingListDAO interface
	
private JdbcTemplate jdbcTemplate;
	
	public TimingsListDAOImpl(DataSource dataSource) {  //getting database connection through dataSource
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<TimingsList> gettime() {
		
		ArrayList<TimingsList> list = new ArrayList<TimingsList>();  //To get response in array we taken array list here.
		TimingsList tl = new TimingsList();
		String timings = "select dayofweek,date from timeslot "; //fetching all time slots from this query.
		
		List<TimingsList> time = jdbcTemplate.query(timings,new ResultSetExtractor<List<TimingsList>>() {

			@Override
			public List<TimingsList> extractData(ResultSet rs) throws SQLException, DataAccessException {
				ArrayList<Timings> al = new ArrayList<Timings>();
							
				while(rs.next()) {				//while loop repeating here.
					
					Timings tm = new Timings();
					tm.setDayofWeek(rs.getString(1));
					tm.setDate(rs.getString(2));
					System.out.println("day of week..." +rs.getString(1));
					 String sql2 = "SELECT * FROM timeslot ";
				        
						jdbcTemplate.query(sql2, new ResultSetExtractor<TimeSlot>() {

							@Override
							public TimeSlot extractData(ResultSet rs1) throws SQLException,
									DataAccessException {
								ArrayList<TimeSlot> tlist=new ArrayList<TimeSlot>();
								if(rs1.next()) {
									TimeSlot t = new TimeSlot();
									t.setTime1(rs1.getString(3));
									t.setTime2(rs1.getString(4));
									t.setTime3(rs1.getString(5));
									tlist.add(t);
								}
								tm.setTimeSlots(tlist);
								return null;
							}
						});
					al.add(tm);
					tl.setTimings(al);
				}
				tl.setMessage("fetched successfull");
				tl.setResponse("3");
				list.add(tl);
				return list;
				
			}
			
		});
		return time;
		
		
	}

}
