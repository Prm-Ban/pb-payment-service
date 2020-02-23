package com.sunwell.payment.controllers;

import java.util.Arrays;




import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sunwell.payment.dto.SalesInvoiceDTO;
import com.sunwell.payment.model.Payment;
import com.sunwell.payment.model.SalesInvoice;
import com.sunwell.payment.services.SalesService;
import com.sunwell.payment.utils.Filters;
import com.sunwell.payment.utils.ServiceUtil;


@RestController
public class SalesController
{
	@Autowired
	SalesService salesSvc ;
	
	@Autowired
	ServiceUtil svcUtil;
	
	
	@RequestMapping(value = "resources/salesinvoices", method = RequestMethod.GET,
			produces = "application/json"
	)
    public ResponseEntity<Map<String,Object>> getSalesInvoices(
    		@RequestHeader(value="Authorization", required = false) String _auth,
    		@RequestParam(value="systemId", required = false) Long _systemId,
    		@RequestParam(value="invoiceNo", required = false) String _invNo,
    		@RequestParam(value="customerId", required = false) Long _custId,
    		Pageable _page ) throws Exception 
    {
		Map<String,Object> retData;

		try {
			Object mainData = null;
			SalesInvoice si = null;
    		Page<SalesInvoice> pageSI = null ;
    		int totalPages = 0;
			long totalItems = 0;
			
			if(_systemId != null) 
				si = salesSvc.findSalesInvoice(_systemId);
            else if(_invNo != null)
        		si = salesSvc.findSalesInvoiceByNo(_invNo);
            else {
            	if(_custId != null) {
        			pageSI = salesSvc.findSalesInvoicesByCustId(_custId, _page);
            	}
            	else
            		pageSI = salesSvc.findAllSalesInvoice(_page);
            }
            
            if(pageSI != null && pageSI.getNumberOfElements() > 0) {
            	List<SalesInvoice> invoices = pageSI.getContent();
            	List<SalesInvoiceDTO> invoicesData = new LinkedList<>();
            	for(SalesInvoice s : invoices)
            		invoicesData.add(new SalesInvoiceDTO(s));
            	mainData = invoicesData;
            	totalPages = pageSI.getTotalPages();
            	totalItems = pageSI.getTotalElements();
    		}
    		else if (si != null) {
    			mainData = new SalesInvoiceDTO(si);
    			totalPages = 1;
    			totalItems = 1;
    		}
            
            retData = svcUtil.returnSuccessfulData(mainData, totalPages, totalItems);
		}
		catch(Exception e) {
			retData = svcUtil.handleException(e);
		}
        return new ResponseEntity<Map<String,Object>>(retData, null, HttpStatus.OK);
    }
	
	@RequestMapping(value = "resources/salesinvoices", method = RequestMethod.GET,
			produces = "application/json", params="criteria"
	)
    public ResponseEntity<Map<String,Object>> getSalesInvoices(
    		@RequestHeader(value="Authorization", required = false) String _auth,
    		@RequestParam(value="criteria") List<String> _filters,
    		Pageable _page
    		) throws Exception 
    {
		Map<String,Object> retData = null;
	
		try {
			
			Object mainData = null;
    		Page<SalesInvoice> pageSI = null ;
			int totalPages = 0;
			long totalItems = 0;
			Filters filters =  svcUtil.convertToFilters(_filters, SalesInvoice.class);			
			pageSI = salesSvc.findSalesInvoices(filters, _page);
			if(pageSI != null && pageSI.getNumberOfElements() > 0) {
            	totalPages = pageSI.getTotalPages();
            	totalItems = pageSI.getTotalElements();
            	List<SalesInvoice> salesInvoices = pageSI.getContent();            	
            	List<SalesInvoiceDTO> listSIDTO = new LinkedList<>();
            	for(SalesInvoice s : salesInvoices) {
            		listSIDTO.add(new SalesInvoiceDTO(s));
            	}
            	mainData = listSIDTO;
        	}
            
            retData = svcUtil.returnSuccessfulData(mainData, totalPages, totalItems);
		}
		catch(Exception e) {
			retData = svcUtil.handleException(e);
		}
		
        return new ResponseEntity<Map<String,Object>>(retData, null, HttpStatus.OK);
    }
	
	@RequestMapping(value = "resources/salesinvoices", method = RequestMethod.POST,
			consumes = "application/json", produces = "application/json"
	)
    public ResponseEntity<Map<String,Object>> createPayment(
    		@RequestHeader(value="Authorization", required = false) String _auth,
    		@RequestBody Payment _payment) throws Exception  
	{        
		Map<String,Object> retData;
		try {	
            SalesInvoice data = salesSvc.createPayment(_payment);
            retData = svcUtil.returnSuccessfulData(new SalesInvoiceDTO(data), 1, 1);
		}
		catch(Exception e) {
			retData = svcUtil.handleException(e);
		}
        return new ResponseEntity<Map<String,Object>>(retData, null, HttpStatus.CREATED);
    }
	
	@RequestMapping(value = "resources/salesinvoices", method = RequestMethod.PUT,
			consumes = "application/json", produces = "application/json"
	)
    public ResponseEntity<Map<String,Object>> editPayment(
    		@RequestHeader(value="Authorization", required = false) String _auth,
    		@RequestBody Payment _payment) throws Exception  
	{        
		Map<String,Object> retData;
		try {	
            SalesInvoice data = salesSvc.editPayment(_payment);
            retData = svcUtil.returnSuccessfulData(new SalesInvoiceDTO(data), 1, 1);
		}
		catch(Exception e) {
			retData = svcUtil.handleException(e);
		}
        return new ResponseEntity<Map<String,Object>>(retData, null, HttpStatus.CREATED);
    }
	
	@RequestMapping(value = "resources/salesinvoices", method = RequestMethod.DELETE,
			produces = "application/json"
	)
	public ResponseEntity<Map<String,Object>> deleteSalesInvoice(
    		@RequestHeader(value="Authorization", required = false) String _auth,
    		@RequestParam("systemId") Long _systemId) throws Exception 
	{
		Map<String,Object> retData;
    	try {
    		SalesInvoice data = salesSvc.deleteSalesInvoice(_systemId);
            retData = svcUtil.returnSuccessfulData(new SalesInvoiceDTO(data), 1, 1);
    	}
    	catch(Exception e) {
    		retData = svcUtil.handleException(e);
    	}
        return new ResponseEntity<Map<String,Object>>(retData, null, HttpStatus.OK);
    }  
}
