package com.cognizant.service;

import java.util.List;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import com.cognizant.dao.ApplyHomeLoanDao;
import com.cognizant.entity.HomeLoan;
import com.cognizant.entity.UserDetails;

@Component
@Transactional
public class ApplyHomeLoanService {
@Autowired
	ApplyHomeLoanDao applyHomeLoanDao;
Random random =new Random();
UserDetails userDetails=new UserDetails ();
ApplyHomeLoanService applyHomeLoanService;
public void insertHomeDetails(UserDetails userDetails) {
	
	
//for(HomeLoan home:userDetails)
	
	
	applyHomeLoanDao.insertHomeDetails(userDetails);
	
}

public String generateRandomNumber(long accoNum ){
	
	String a=String.valueOf(accoNum).substring(13);
	String id="HL-"+a;
	return id;
}

public long generateLoanAccNumber() {
	Long num=(long) (Math.random() * 100000000000000L);
	return num;
}

public void updateHomeLoanDetails(long l, HomeLoan e6) {
	
	userDetails=applyHomeLoanDao.updateHomeLoanDetails(l);
	System.out.println("again");
	
	List<HomeLoan> list=userDetails.getHomeLoan();

	e6.setUser(userDetails);

	
list.add(e6);
userDetails.setHomeLoan(list);
	System.out.println(userDetails);
	
 

	
}
	
}

