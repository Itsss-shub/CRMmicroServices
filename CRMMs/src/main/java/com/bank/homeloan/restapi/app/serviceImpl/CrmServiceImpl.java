package com.bank.homeloan.restapi.app.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.homeloan.restapi.app.exception.EnquiryNotFoundException;
import com.bank.homeloan.restapi.app.model.Enquiry;
import com.bank.homeloan.restapi.app.repository.CrmRepo;
import com.bank.homeloan.restapi.app.service.CrmService;


@Service
public class CrmServiceImpl implements CrmService {
	
	@Autowired
	CrmRepo cr;

	@Override
	public List<Enquiry> getAllEnquiry() {
		
		 List<Enquiry> ls=cr.findAll();
		if(!ls.isEmpty()) {
			return ls;
		}else {
			throw new EnquiryNotFoundException("no Enquiry found on the data base..!!!");
		}
	}



	@Override
	public Enquiry updateEnquiry( Integer enquiryId) {
		
		Optional<Enquiry> enq = cr.findById(enquiryId);
		
		if(enq!=null) {
		Enquiry enquiry = enq.get();
		
		enquiry.setStatus("FTOE");
		
		return cr.save(enquiry);
		}
		else {
			throw new EnquiryNotFoundException("no enquiry found on this id ..!!!");
		}
	}

	
	@Override
	public List<Enquiry> getPendingEnquiry() {
		List<Enquiry> pending= cr.findAllByStatus("pending");
		if(!pending.isEmpty()) {
		return pending;
		}
		else {
			throw new EnquiryNotFoundException("no pending Enqiry found");
		}
	}

	@Override
	public List<Enquiry> getAprovedEnquiry() {
		List<Enquiry> approved= cr.findAllByStatus("approved");
		if(!approved.isEmpty()) {
		return approved;
		}
		else {
			throw new EnquiryNotFoundException("no approved Enqiry found");
		}
	}



	@Override
	public List<Enquiry> getRejectedEnquiry() {
		
		List<Enquiry> rejected= cr.findAllByStatus("rejected");
		if(!rejected.isEmpty()) {
		return rejected;
		}
		else {
			throw new EnquiryNotFoundException("no rejected Enqiry found");
		}
	}

}
