package com.cognizant.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.cognizant.entity.EducationLoan;
import com.cognizant.entity.UserDetails;

@Component
public class ViewEducationLoanDAO {

	@PersistenceContext
	EntityManager em;
	
	@Transactional
	public List<EducationLoan> retriveEducationLoanDetails(long i) {
		// TODO Auto-generated method stub
		System.out.println("in dao");
		UserDetails retrive=em.find(UserDetails.class,i); 
		System.out.println("after retrieval");
		System.out.println(retrive);
		System.out.println("z");
		System.out.println(retrive.getEduLoan());
		
		return retrive.getEduLoan();
		
	

}
}
