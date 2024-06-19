package com.bank.homeloan.restapi.app.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandller {
	
	@ExceptionHandler(EnquiryNotFoundException.class)
	public ResponseEntity<APIError> HandleEnquiryNotFound(EnquiryNotFoundException enf, HttpServletRequest hsr){
		APIError ap=new APIError();
		ap.setStatuscode(HttpStatus.NOT_FOUND.value());
		ap.setPath(hsr.getRequestURI());
		ap.setMessage(enf.getMessage());
		ap.setDate(new Date());
		ap.setHttpmsg(HttpStatus.NOT_FOUND);
		
		return new ResponseEntity<APIError>(ap,HttpStatus.NOT_FOUND);
	}
	
	
	@ExceptionHandler(CustomerDetailsApplictionNotFoundException.class)
	public ResponseEntity<APIError> handleCustomerDetailsApplictionNotFoundException(CustomerDetailsApplictionNotFoundException enf, HttpServletRequest hsr){
		APIError ap=new APIError();
		ap.setStatuscode(HttpStatus.NOT_FOUND.value());
		ap.setPath(hsr.getRequestURI());
		ap.setMessage(enf.getMessage());
		ap.setDate(new Date());
		ap.setHttpmsg(HttpStatus.NOT_FOUND);
		
		return new ResponseEntity<APIError>(ap,HttpStatus.NOT_FOUND);
	}
	

}
