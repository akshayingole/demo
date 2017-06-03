package com.cognizant.dao;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;



import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.cognizant.entity.UserDetails;


@Component
public class ApplyEducationLoanDAO {
	
	 @PersistenceContext
	 private EntityManager em;
	


	    @Transactional
		public void insertEducationLoanDetails(UserDetails user) {
			//session.save() - em.persist()
			em.persist(user);
			System.out.println("in dao");
			
			
		}

	    @Transactional
		public UserDetails updateEducationLoanDetails(long i) {
			// TODO Auto-generated method stub
	    	UserDetails user=em.find(UserDetails.class,i); 
			System.out.println("after retrieval");
			System.out.println(user);
	
			return user;
		}

		
	
	
	
	
	
	
	

}
