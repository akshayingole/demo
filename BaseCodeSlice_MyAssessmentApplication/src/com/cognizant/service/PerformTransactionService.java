package com.cognizant.service;

import java.util.ArrayList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import com.cognizant.dao.PerformTransactionDAO;
import com.cognizant.entity.UserDetails;
import com.cognizant.exception.InvalidAccountBalance;
import com.cognizant.vo.TransactionDetailsVO;

@Component
public class PerformTransactionService {

	@Autowired
	PerformTransactionDAO ptDAO;
	InvalidAccountBalance invalidAccountBalance ;
	
	@Transactional
	public void updateTransactionDetails(TransactionDetailsVO transaction,Long accountNumber) throws  InvalidAccountBalance {
		  System.out.println("inside service"); 
		  UserDetails user=ptDAO.updateTransactionDetails(accountNumber);
          
		  transaction.setUser(user);
          
		  List<TransactionDetailsVO> transactionList=new ArrayList<TransactionDetailsVO>();
          System.out.println(transaction);
          transactionList.add(transaction);
          user.setTdDetails(transactionList);
          System.out.println(user);
          //updates the balance in user table
          updateUser(transaction.getTransactionAmount(),user,transaction.getTransactionType());
          
		
	}


		private void updateUser(int transactionAmount, UserDetails user,String transactionType) throws InvalidAccountBalance {
			int newBalance=0;
			
			if(transactionType.equals("DEPOSIT")){
				newBalance=user.getAccountBalance()+transactionAmount;
				user.setAccountBalance(newBalance);
			}
			
			else{
				newBalance=user.getAccountBalance()-transactionAmount;
			
			
				if(newBalance<5000 && user.getAccountType().equals("SAVINGS")){
				
					throw new InvalidAccountBalance("Saving Account's Balance can't be less than 5000");
				
			}
				else if(newBalance<0){
				
					throw new InvalidAccountBalance("Insufficient Balance");
				}
			
			else{
			
				user.setAccountBalance(newBalance);
				}
			}
		
	}


		@Transactional
		public List<TransactionDetailsVO> retrieveTransactionDetails(Long accountNumber) {
			List<TransactionDetailsVO> transactionDetails =ptDAO.retrieveTransactionDetails(accountNumber);
			return transactionDetails;
		
	}
		
	
}
