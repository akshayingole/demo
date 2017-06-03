package com.cognizant.controller;

import java.util.Iterator;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.cognizant.entity.HomeLoan;
import com.cognizant.service.ApplyHomeLoanService;


@Controller
public class ApplyHomeLoanController {
	@Autowired
	ApplyHomeLoanService applyHomeLoanService;
	
	@RequestMapping(value = "/applyHomeLoan", method=RequestMethod.GET)
	public String getHOmeLoan(Model model){
		
		model.addAttribute("homeLoan", new HomeLoan());
		return "applyHomeLoan";
	}
	@RequestMapping(value = "/applyHomeLoan", method = RequestMethod.POST)
	public String initiateHomeLoan(
		@ModelAttribute("homeLoan") @Valid HomeLoan homeLoan,
		BindingResult result, 
		Model model) {
		long loanAccNum=applyHomeLoanService.generateLoanAccNumber();
		String id=applyHomeLoanService.generateRandomNumber(1234567890123546l);
		homeLoan.setLoanAccountNumber(loanAccNum);
		homeLoan.setHomeLoanId(id);
		System.out.println(homeLoan);
		//homeLoans = new ArrayList<>();	
		//homeLoans.add(homeLoan);
		//userDetails.setHomeLoan(homeLoans);
		
		try {
			applyHomeLoanService.updateHomeLoanDetails(1234567890123546L,homeLoan);
		} 
		catch (ConstraintViolationException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();
			Iterator<ConstraintViolation<?>> iterator = constraintViolations.iterator();
			while(iterator.hasNext()){
				ConstraintViolation<?> next = iterator.next();
				System.out.println("Validation message: "+next.getMessage());
				System.out.println("Invalid field: "+next.getPropertyPath());
				System.out.println("Validation class/bean: "+next.getRootBean());
//				 result.reject(next.getPropertyPath(), next.getMessage());
				result.rejectValue(next.getPropertyPath().toString(), "", next.getMessage());
//				result.rejectValue(arg0, arg1, arg2);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	model.addAttribute("m1", homeLoan.getLoanAccountNumber());
	model.addAttribute("m2", homeLoan.getHomeLoanId());


	if (result.hasErrors()) {
		return "applyHomeLoan";
	} 
		return "successHomeLoan";

	}
}
