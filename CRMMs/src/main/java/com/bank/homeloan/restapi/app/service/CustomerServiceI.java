package com.bank.homeloan.restapi.app.service;

import java.util.List;

import com.bank.homeloan.restapi.app.model.CustomerDetails;

public interface CustomerServiceI {

	CustomerDetails savedata(CustomerDetails cd);

	List<CustomerDetails> getByStatus();

	CustomerDetails updateStauts(Integer customerDetailsId, CustomerDetails c);

	List<CustomerDetails> allAppl();

}
