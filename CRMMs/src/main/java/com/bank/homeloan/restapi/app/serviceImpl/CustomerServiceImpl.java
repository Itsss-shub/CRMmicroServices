package com.bank.homeloan.restapi.app.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.bank.homeloan.restapi.app.exception.CustomerDetailsApplictionNotFoundException;
import com.bank.homeloan.restapi.app.model.CustomerDetails;
import com.bank.homeloan.restapi.app.repository.CustomerRepo;
import com.bank.homeloan.restapi.app.service.CustomerServiceI;

@Service
public class CustomerServiceImpl implements CustomerServiceI{

	@Autowired
	CustomerRepo cri;
	
   @Autowired private JavaMailSender jmsender;
	
	@Autowired @Value("$spring.mail.username") String myMail;

	@Override
	public CustomerDetails savedata(CustomerDetails cd) {
		
		
		cd.setStatus("pending");
		
		String sub = "Loan Application Submission - Apna Finance Corp Ltd";
				 String msg = "Dear "+ cd.getCustomerName()+",\n\n" +
				      "Thank you for submitting your loan application along with the required documents to Apna Finance Corp Ltd. "+
				                "We have successfully received your application and it is currently under review.\n\n" +
				                "Our team will process your application and verify the submitted documents. We will notify you once the review process is complete and inform you of the next steps.\n\n" +
				                "If you have any further questions or need assistance, please feel free to contact our support team.\n\n" +
				                "Best Regards,\n" +
				                "Apna Finance Corp Ltd\n" +
				                "Customer Support Team\n" +
				                "Email: support@apnafinance.com\n" +
				                "Phone: +91 8088080880";
		SimpleMailMessage message=new SimpleMailMessage();
		message.setFrom(myMail);
		message.setTo(cd.getCustomerEmailId());
		message.setSubject(sub);
		message.setText(msg);
		
		jmsender.send(message);
		
		return cri.save(cd);
		
	}

	@Override
	public List<CustomerDetails> allAppl() {
		List<CustomerDetails> app = cri.findAll();
		if(!app.isEmpty()) {
			
		return app;
		}
		else {
			throw new CustomerDetailsApplictionNotFoundException("No Application found on the database");
		}
	}
	
	@Override
	public List<CustomerDetails> getByStatus() {
		List<CustomerDetails> app = cri.findByStatus("pending");
		if(!app.isEmpty()) {
			
		return app;
		}
		else {
			
			throw new CustomerDetailsApplictionNotFoundException("No Application found on this Status : pending");
		}
	}

	@Override
	public CustomerDetails updateStauts(Integer customerDetailsId, CustomerDetails c) {
		Optional<CustomerDetails> app = cri.findById(customerDetailsId);
		if(app!=null) {
			CustomerDetails appl = app.get();
			appl.setStatus("FTOE");
		return cri.save(appl);
		}
		else {
			throw new CustomerDetailsApplictionNotFoundException("No Application found on this id : "+customerDetailsId);
		}
	}

	
}
