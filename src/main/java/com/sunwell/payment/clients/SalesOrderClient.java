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

//    @Autowired
//    OrganizationRedisRepository orgRedisRepo;

    private static final Logger logger = LoggerFactory.getLogger(SalesOrderClient.class);
//
//    private Organization checkRedisCache(String organizationId) {
//        try {
//            return orgRedisRepo.findOrganization(organizationId);
//        }
//        catch (Exception ex){
//            logger.error("Error encountered while trying to retrieve organization {} check Redis Cache.  Exception {}", organizationId, ex);
//            return null;
//        }
//    }

//    private void cacheOrganizationObject(Organization org) {
//        try {
//            orgRedisRepo.saveOrganization(org);
//        }catch (Exception ex){
//            logger.error("Unable to cache organization {} in Redis. Exception {}", org.getId(), ex);
//        }
//    }

    public SalesInvoice getSalesOrder(long _soId){
        logger.debug("In Licensing Service.getOrganization: {}", UserContext.getCorrelationId());

//        Organization org = checkRedisCache(organizationId);
//
//        if (org!=null){
//            logger.debug("I have successfully retrieved an organization {} from the redis cache: {}", organizationId, org);
//            return org;
//        }
//
//        logger.debug("Unable to locate organization from the redis cache: {}.", organizationId);

        ResponseEntity<SalesInvoice> restExchange =
                restTemplate.exchange(
                        "http://zuul-service/sales-service/salesservice/resources/salesorders?systemId={systemId}",
                        HttpMethod.GET,
                        null, SalesInvoice.class, _soId);
        
//        ResponseEntity<SalesInvoice> restExchange =
//                restTemplate.exchange(
//                        "http://localhost:5555/sales-service/salesservice/resources/salesorders?systemId={systemId}",
//                        HttpMethod.GET,
//                        null, SalesInvoice.class, _soId);

        /*Save the record from cache*/
        SalesInvoice si  = restExchange.getBody();
       
        return si;
    }
    
    public SalesInvoice deleteSalesOrder(long _soId){
        logger.debug("In Licensing Service.getOrganization: {}", UserContext.getCorrelationId());

//        Organization org = checkRedisCache(organizationId);
//
//        if (org!=null){
//            logger.debug("I have successfully retrieved an organization {} from the redis cache: {}", organizationId, org);
//            return org;
//        }
//
//        logger.debug("Unable to locate organization from the redis cache: {}.", organizationId);

        ResponseEntity<SalesInvoice> restExchange =
                restTemplate.exchange(
                        "http://zuul-service/sales-service/salesservice/resources/salesorders?systemId={systemId}",
                        HttpMethod.DELETE,
                        null, SalesInvoice.class, _soId);
        
//        ResponseEntity<SalesInvoice> restExchange =
//                restTemplate.exchange(
//                        "http://localhost:5555/sales-service/salesservice/resources/salesorders?systemId={systemId}",
//                        HttpMethod.DELETE,
//                        null, SalesInvoice.class, _soId);

        /*Save the record from cache*/
        SalesInvoice si  = restExchange.getBody();
       
        return si;
    }


}
