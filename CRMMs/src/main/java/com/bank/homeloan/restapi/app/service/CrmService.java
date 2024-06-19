package com.bank.homeloan.restapi.app.service;

import java.util.List;

import com.bank.homeloan.restapi.app.model.Enquiry;

public interface CrmService {

	 public List<Enquiry> getAllEnquiry();

	

	public Enquiry updateEnquiry(Integer enquiryId);
	
	List<Enquiry> getPendingEnquiry();

	List<Enquiry> getAprovedEnquiry();



	public List<Enquiry> getRejectedEnquiry();

}
