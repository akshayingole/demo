package com.cognizant.controller;


import java.util.ArrayList;

import java.util.List;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.cognizant.entity.HomeLoan;
import com.cognizant.service.ViewHomeLoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;

@Controller
public class ViewHomeLoanController {
	
	@Autowired
	ViewHomeLoanService viewHomeLoanService;
	private List<HomeLoan> homeLoans=new ArrayList<HomeLoan>();
	
	@RequestMapping(value = "/viewHomeLoan", method=RequestMethod.GET)
	public String getHOmeLoan(){
		
		return "viewHomeLoan";
	}
	@RequestMapping(value="/viewHomeLoan1",method=RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody  List<HomeLoan> gethomeLoan(){
		 homeLoans = viewHomeLoanService.retrieveHomeDetails(1234567890123546L);
		
//		UserDetails userDetails=new UserDetails(1234567897853456L,"credit","akshay,",47855);
//		UserDetails userDetails2=new UserDetails(1231457890123456L,"saving","sumit,",4785655);
//		details = new ArrayList<>();
//		details.add(userDetails);
//		details.add(userDetails2);
		 System.out.println("in control");
			
		 System.out.println(homeLoans);
	
		return homeLoans;
	
	}
	
}
