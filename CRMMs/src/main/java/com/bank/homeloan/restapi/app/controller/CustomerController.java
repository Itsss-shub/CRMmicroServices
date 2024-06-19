package com.bank.homeloan.restapi.app.controller;


import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bank.homeloan.restapi.app.model.AllPersonalDocs;
import com.bank.homeloan.restapi.app.model.CustomerDetails;
import com.bank.homeloan.restapi.app.service.CustomerServiceI;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@CrossOrigin("*")
@Slf4j
@RestController
@RequestMapping("/bank/homeloan/restapi/customer")
public class CustomerController {
	
	@Autowired
	CustomerServiceI csi;
	
	@PostMapping(value = "/saveCustomer", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
	public ResponseEntity<CustomerDetails> saveCustomerDetails(
			@RequestPart ("addressProof") MultipartFile addressProof, 
			@RequestPart ("panCard") MultipartFile panCard, 
			@RequestPart ("aadharCard") MultipartFile aadharCard, 
			@RequestPart ("incomeTaxReturn") MultipartFile incomeTaxReturn, 
			@RequestPart ("photo") MultipartFile photo, 
			@RequestPart ("signature") MultipartFile signature, 
			@RequestPart ("thumb") MultipartFile thumb, 
			@RequestPart ("bankCheque") MultipartFile bankCheque, 
			@RequestPart ("bankStatement") MultipartFile bankStatement, 
			@RequestPart ("propertyProof") MultipartFile propertyProof, 
			@RequestPart ("incomeProof") MultipartFile incomeProof, 
			@RequestPart ("propertyInsurance") MultipartFile propertyInsurance, 
			@RequestPart ("professionalSalarySlip") MultipartFile professionalSalarySlip, 
			@RequestPart("data") String data
			) throws IOException{
		ObjectMapper om=new ObjectMapper();
		CustomerDetails cd=om.readValue(data, CustomerDetails.class);
		
		AllPersonalDocs doc=cd.getAllPersonalDocs();
		doc.setAddressProof(addressProof.getBytes());
		doc.setPanCard(panCard.getBytes());
		doc.setAadharCard(aadharCard.getBytes());
		doc.setIncomeTaxReturn(incomeTaxReturn.getBytes());
		doc.setPhoto(photo.getBytes());
		doc.setSignature(signature.getBytes());
		doc.setThumb(thumb.getBytes());
		doc.setBankCheque(bankCheque.getBytes());
		doc.setBankStatement(bankStatement.getBytes());
		doc.setPropertyProof(propertyProof.getBytes());
		doc.setIncomeProof(incomeProof.getBytes());
		doc.setPropertyInsurance(propertyInsurance.getBytes());
		doc.setProfessionalSalarySlip(professionalSalarySlip.getBytes());
		CustomerDetails cdd= csi.savedata(cd);
		log.info("customer information saved..!!!");
		return new ResponseEntity<CustomerDetails>(cdd,HttpStatus.CREATED);
		
	}
	

	@GetMapping("/getAllApplication")
	public ResponseEntity<List<CustomerDetails>> allAppl(){
		List<CustomerDetails> applist = csi.allAppl();
		log.info("All customer Application fetched..!!!");
		return new ResponseEntity<List<CustomerDetails>>(applist,HttpStatus.OK);
	}
	
	@GetMapping("/getAllApplicationWithPending")
	public ResponseEntity<List<CustomerDetails>> allApplwithStatus(){
		List<CustomerDetails> applist = csi.getByStatus();
		log.info("All customer Application fetched with Status : "+" pending");
		return new ResponseEntity<List<CustomerDetails>>(applist,HttpStatus.OK);
	}
	
	@PatchMapping("/updateStatus/{customerDetailsId}")
	public ResponseEntity<CustomerDetails> updateStatus(@PathVariable Integer customerDetailsId,@RequestBody CustomerDetails c){
		CustomerDetails app = csi.updateStauts(customerDetailsId,c);
		log.info(" customer Application Status changed : "+"FTOE");
		return new ResponseEntity<CustomerDetails>(app,HttpStatus.OK);
	}
}
