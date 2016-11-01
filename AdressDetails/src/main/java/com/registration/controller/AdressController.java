package com.registration.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.registration.dao.AdressDAO;
import com.registration.vo.Address;

@Controller
@RequestMapping("/address")
public class AdressController {
	@Autowired
	AdressDAO addressDAO;
	@Value("${URL}")
	private String URL;

	
	
	@RequestMapping(method=RequestMethod.POST)
	public @ResponseBody Address addressDetails(@RequestParam("adharNumber") String adharNumber)
	{
		return addressDAO.getAddressDetails(adharNumber);
	}
}
