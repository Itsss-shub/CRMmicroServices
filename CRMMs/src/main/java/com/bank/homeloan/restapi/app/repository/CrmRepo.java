package com.bank.homeloan.restapi.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bank.homeloan.restapi.app.model.Enquiry;


@Repository
public interface CrmRepo extends JpaRepository<Enquiry, Integer>{
	
	public List<Enquiry> findAllByStatus(String status);
	

}
