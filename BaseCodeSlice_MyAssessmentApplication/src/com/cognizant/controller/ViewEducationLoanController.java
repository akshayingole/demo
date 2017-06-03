package com.cognizant.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cognizant.entity.EducationLoan;
import com.cognizant.service.ViewEducationLoanService;

@Controller
public class ViewEducationLoanController {
	
	
	@Autowired
	ViewEducationLoanService service;
	List<EducationLoan> eduLoan;
	
	@RequestMapping(value = "/viewEducationLoanDetails", method=RequestMethod.GET)
	public String getEducationLoans(){
		
		
		return "viewEducationLoanDetails";
	}
	
	@RequestMapping(value="/viewEducationLoanDetails1",method=RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)

	public @ResponseBody  List<EducationLoan> getEduLoan(){
		
		List<EducationLoan> eduLoan = service.retriveEducationLoanDetails(1234567890123546L);
		

		 System.out.println("in control");
			
		 System.out.println(eduLoan);
	
		return eduLoan;
	
	}
	
}

