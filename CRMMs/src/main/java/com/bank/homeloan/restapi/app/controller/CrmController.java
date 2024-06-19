package com.bank.homeloan.restapi.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.homeloan.restapi.app.model.Enquiry;
import com.bank.homeloan.restapi.app.service.CrmService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.extern.slf4j.Slf4j;


@CrossOrigin("*")
@Slf4j
@RestController
@RequestMapping("/bank/homeloan/restapi/enquiry")
public class CrmController {
	
	@Autowired
	CrmService cs;
	
	
	@GetMapping("/allenquiry")
	public ResponseEntity<List<Enquiry>> getAllEnquiry(@RequestBody Enquiry e ){
		
		List<Enquiry> enq = cs.getAllEnquiry();
		 log.info(" Fetched all enquiries..!");

		return new ResponseEntity<List<Enquiry>>(enq, HttpStatus.OK);
		
	}
	
	@PutMapping("/updatestatus/{enquiryId}")
	public ResponseEntity<Enquiry> updateEnquiry(@PathVariable Integer enquiryId ,@RequestBody Enquiry e){
		
		 Enquiry us =cs.updateEnquiry(enquiryId);
		 log.info("status update to FTOE..!");
		 return new ResponseEntity<Enquiry>(us,HttpStatus.OK);
		
	}
	

	
	@GetMapping("/pending")
	public ResponseEntity<List<Enquiry>> getPendingEnquiry(){
		
		List<Enquiry> enq = cs.getPendingEnquiry();
		log.info(" Fetched all enquiries with pending status..!");

		return new ResponseEntity<List<Enquiry>>(enq,HttpStatus.OK);
	}
	
	@GetMapping("/approved")
	public ResponseEntity<List<Enquiry>> getAprovedEnquiry(){
		
		List<Enquiry> enq = cs.getAprovedEnquiry();
		log.info(" Fetched all enquiries with approved status..!");

		return new ResponseEntity<List<Enquiry>>(enq,HttpStatus.OK);
	}	
	
	@GetMapping("/rejected")
	public ResponseEntity<List<Enquiry>> getRejcetedEnquiry(){
		
		List<Enquiry> enq = cs.getRejectedEnquiry();
		log.info(" Fetched all enquiries with rejected status..!");

		return new ResponseEntity<List<Enquiry>>(enq,HttpStatus.OK);
	}	

}
