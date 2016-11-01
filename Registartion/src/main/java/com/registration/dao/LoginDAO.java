package com.registration.dao;

import com.registration.vo.User;
import com.registration.vo.Vehical;

public interface LoginDAO {
	
	boolean checkLogin(User user);
	Vehical registerVehical(Vehical vehical,String url);
	

}
