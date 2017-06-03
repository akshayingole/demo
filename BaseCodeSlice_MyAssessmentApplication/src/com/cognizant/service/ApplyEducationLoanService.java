package com.cognizant.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import com.cognizant.dao.ApplyEducationLoanDAO;
import com.cognizant.entity.EducationLoan;
import com.cognizant.entity.UserDetails;

@Component
@Transactional
public class ApplyEducationLoanService {

	@Autowired
	ApplyEducationLoanDAO dao;
	EducationLoan edu;
	UserDetails user;
	
	

	public String generateLoanId(int idcardno){
		
		String idcard=String.valueOf(idcardno).substring(0,5);
		String id="EL_"+idcard;
		return id;
	}

	public long generateLoanAccountNumber() {
		Long num=(long) (Math.random() * 100000000000000L);
		return num;
	}
	
	public void insertEducationLoanDetails(UserDetails user) {
		// TODO Auto-generated method stub
		dao.insertEducationLoanDetails(user);
		System.out.println("inservice");
	}



	public void updateEducationLoanDetails(EducationLoan e1, long i) {
		// TODO Auto-generated method stub
		user= dao.updateEducationLoanDetails(i);
		
		List<EducationLoan> eduList=user.getEduLoan();
		e1.setUser(user);
		System.out.println(e1);
		eduList.add(e1);
		user.setEduLoan(eduList);
		System.out.println(user);
		
		
	}




			
		}
	


