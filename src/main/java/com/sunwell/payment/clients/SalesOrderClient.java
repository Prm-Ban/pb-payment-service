package com.sunwell.payment.clients;

import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.sunwell.payment.model.SalesInvoice;
import com.sunwell.payment.utils.UserContext;

@Component
public class SalesOrderClient {
	
    @Autowired
    RestTemplate restTemplate;

    private static final Logger logger = LoggerFactory.getLogger(SalesOrderClient.class);
    public SalesInvoice getSalesOrder(long _soId){
        logger.debug("In getSalesOrder(long _soId)", UserContext.getCorrelationId());

        ResponseEntity<SalesInvoice> restExchange =
                restTemplate.exchange(
                        "http://zuul-service/sales-service/salesservice/resources/salesorders?systemId={systemId}",
                        HttpMethod.GET,
                        null, SalesInvoice.class, _soId);

        /*Save the record from cache*/
        SalesInvoice si  = restExchange.getBody();
       
        return si;
    }
    
    public SalesInvoice deleteSalesOrder(long _soId){
        logger.debug("In deleteSalesOrder(long _soId)", UserContext.getCorrelationId());

        ResponseEntity<SalesInvoice> restExchange =
                restTemplate.exchange(
                        "http://zuul-service/sales-service/salesservice/resources/salesorders?systemId={systemId}",
                        HttpMethod.DELETE,
                        null, SalesInvoice.class, _soId);

        /*Save the record from cache*/
        SalesInvoice si  = restExchange.getBody();
       
        return si;
    }
}
