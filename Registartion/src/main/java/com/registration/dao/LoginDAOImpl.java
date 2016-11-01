package com.registration.dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.registration.vo.User;
import com.registration.vo.Vehical;

@Repository
public class LoginDAOImpl implements LoginDAO {
	
	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public boolean checkLogin(User user) {
		StringBuffer query=new StringBuffer();
		query.append("Select count(*) from user_login_details where uname=? and password=?");
		@SuppressWarnings("deprecation")
		int count=jdbcTemplate.queryForInt(query.toString(), new Object[]{user.getUserName(),user.getPassword()});
	    return count>0?true:false;
	
	}

	@Override
	public Vehical registerVehical(Vehical vehical, String url) {
		
		StringBuffer query =new StringBuffer();
		query.append("insert into vehicalregistration (vehicalname,vehicaltype,adharnumber,registrationdate,addressline1,addressline2,town,city,country)  values(?,?,?,CURRENT_TIMESTAMP,?,?,?,?,?)");
		
		String response=getResponse(getConnection(url, "POST")).toString();
		System.out.println("response"+response);
		JSONObject jsonObject=new JSONObject(response);
		vehical.setAddressLine1(jsonObject.getString("addressLine1"));
		vehical.setAddressLine2(jsonObject.getString("addressLine2"));
		vehical.setTown(jsonObject.getString("town"));
		vehical.setCity(jsonObject.getString("city"));
		vehical.setCountry(jsonObject.getString("country"));
		int  count=jdbcTemplate.update(query.toString(), new Object[]{vehical.getVehicalName(),vehical.getVehicalType(),
			vehical.getAdharNumber(),vehical.getAddressLine1(),vehical.getAddressLine2(),vehical.getTown(),vehical.getCity(),
			vehical.getCountry()});
		return count>0?vehical:null;
				
		}

	
	public HttpURLConnection getConnection(String urlValue, String method) {
		try {
			URL url = new URL(urlValue);
			HttpURLConnection jasperURLConnection = (HttpURLConnection) url.openConnection();
			jasperURLConnection.setDoOutput(true);
			jasperURLConnection.setRequestMethod(method);
			jasperURLConnection.setRequestProperty("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE);
			return jasperURLConnection;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (ProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	public String getResponse(HttpURLConnection urlConnection) {
		try {
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader((urlConnection.getInputStream())));
			StringBuilder responseStrBuilder = new StringBuilder();

			String output = "";
			while ((output = bufferedReader.readLine()) != null) {
				responseStrBuilder.append(output);
			}
			closeConnection(urlConnection);
			return responseStrBuilder.toString();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}
	
	public void closeConnection(HttpURLConnection urlConnection) {
		urlConnection.disconnect();
	}
	
}

	


