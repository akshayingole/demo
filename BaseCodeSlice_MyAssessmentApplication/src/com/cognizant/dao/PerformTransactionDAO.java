package com.cognizant.dao;

import java.util.List;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.ImportResource;
import org.springframework.stereotype.Component;

import com.cognizant.entity.UserDetails;
import com.cognizant.vo.TransactionDetailsVO;

@Component
@ImportResource("classpath:beans.xml")
public class PerformTransactionDAO {
	  @PersistenceContext
	  private EntityManager em;
	 	
	
	public UserDetails updateTransactionDetails(Long accountNumber) {
		UserDetails user=em.find(UserDetails.class,accountNumber );
		System.out.println("dao user :"+user);
		return user;
		}
	/*@Transactional
	public String getAccountHolderName(Long accountNumber) {
		Query query = em.createQuery("select account_holder_name from mock_data e where e.account_number= ?");
		query.setParameter(1, accountNumber);
		String userName=(String) query.getSingleResult();
		return userName;
		
	}*/

	public List<TransactionDetailsVO> retrieveTransactionDetails(Long accountNumber) {
		
		UserDetails user=em.find(UserDetails.class, accountNumber);
		System.out.println(user);
		System.out.println(user.getTdDetails());
		List<TransactionDetailsVO> transaction=user.getTdDetails();
		return transaction; 
		
	}

}
