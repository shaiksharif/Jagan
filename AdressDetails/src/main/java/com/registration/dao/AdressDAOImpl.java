package com.registration.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.registration.vo.Address;

@Repository
public class AdressDAOImpl implements AdressDAO {
	
	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public Address getAddressDetails(String adharNumber) {
		StringBuffer query=new StringBuffer();
		query.append("select * from addresstable where adharnumber=?");
		return (Address)jdbcTemplate.queryForObject(query.toString(), new Object[]{adharNumber},new RowMapper<Address>(){
			@Override
			public Address mapRow(ResultSet result, int index) throws SQLException {
				Address address=new Address();
				address.setAddressLine1(result.getString(3));
				address.setAddressLine2(result.getString(4));
				address.setTown(result.getString(5));
				address.setCity(result.getString(6));
				address.setState(result.getString(7));
				address.setCountry(result.getString(8));
				return address;
			}
		});
		
	}
	

	
	
}

	


