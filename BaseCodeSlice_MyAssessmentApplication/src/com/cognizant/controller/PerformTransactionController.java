package com.cognizant.controller;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

import java.util.Iterator;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.cognizant.service.PerformTransactionService;
import com.cognizant.vo.TransactionDetailsVO;

@Controller
public class PerformTransactionController {
	
	 @Autowired
	 PerformTransactionService service;
	 TransactionDetailsVO transactionDetails;
	
	@RequestMapping(value="/addTransaction", method=RequestMethod.GET)
	public String getEmployee(Model model){
		
		model.addAttribute("transaction",new TransactionDetailsVO());
		return "addTransaction";
	}
	
	
	@RequestMapping(value="/addTransaction", method=RequestMethod.POST )
	public String initiateTransaction (@ModelAttribute("transaction") @Valid TransactionDetailsVO transactionDetails, BindingResult result,Model model){
		
		System.out.println("controller :"+transactionDetails);	
		//transactionDetailsList = new ArrayList<TransactionDetailsVO>();
		//transactionDetailsList.add(transactionDetails);
		try {
			service.updateTransactionDetails(transactionDetails,1234567890123546L);
		} 
		catch (ConstraintViolationException e) {
			
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("m1", transactionDetails.getTransactionId());
		

		if (result.hasErrors()) {
			return "addTransaction";
		} 
		
		return "successTransaction";

	}


}
		
