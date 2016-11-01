package com.registration.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.registration.dao.LoginDAO;
import com.registration.vo.User;
import com.registration.vo.Vehical;

@Controller
public class LoginController {
	@Autowired
	LoginDAO loginDAO;
	@Value("${URL}")
	private String URL;

	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public String checkLogin(@ModelAttribute User user)
	{
		
		return loginDAO.checkLogin(user)==true?"vechichalRegistration":"login";
	}
	
	@RequestMapping(value="/vehicalRegister",method=RequestMethod.POST)
	public String vehicalRegister(@ModelAttribute Vehical vehical)
	{
		ModelMap map=new ModelMap();
		Vehical vehicalDetails=loginDAO.registerVehical(vehical, URL+vehical.getAdharNumber());
		map.put("vehical", vehicalDetails);
		
		return "vechichalRegistration";
	}
}
