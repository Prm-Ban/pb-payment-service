package com.sunwell.payment.services;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Calendar;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.sunwell.payment.clients.SalesOrderClient;
import com.sunwell.payment.exception.OperationException;
import com.sunwell.payment.model.Payment;
import com.sunwell.payment.model.PaymentImage;
import com.sunwell.payment.model.SalesInvoice;
import com.sunwell.payment.model.SalesInvoiceItem;
import com.sunwell.payment.model.SalesInvoiceItemPK;
import com.sunwell.payment.repository.PaymentImageRepo;
import com.sunwell.payment.repository.SalesInvoiceItemRepo;
import com.sunwell.payment.repository.SalesInvoiceRepo;
import com.sunwell.payment.utils.StandardConstant;



@Service
@Transactional
@Validated
public class SalesSvc implements SalesService
{
	@Autowired
	SalesInvoiceRepo siRepo;	
	
	@Autowired
	SalesInvoiceItemRepo siItemRepo;
	
	@Autowired
	PaymentImageRepo payImgRepo;
	
	@Autowired
	SalesOrderClient soClient;
	
	public PaymentImage addPaymentImage(@Valid @NotNull(message="{error_no_payment_image}") PaymentImage _pi) {
    	preparePaymentImage(_pi);
		return payImgRepo.save(_pi);
	}
    
    public PaymentImage editPaymentImage(@Valid @NotNull(message="{error_no_payment_image}") PaymentImage _pi) {
    	PaymentImage pi = payImgRepo.findBySalesInvoice(_pi.getSalesInvoice());
		if(pi == null)
			throw new OperationException(StandardConstant.ERROR_CANT_FIND_PAYMENT_IMAGE, null);
		
		preparePaymentImage(_pi);
		
		payImgRepo.save(pi);
		return pi;
	}
    
    public PaymentImage deletePaymentImage(@NotNull(message="{error_no_si}")SalesInvoice _si) {
    	PaymentImage pi = payImgRepo.findById(_si.getSystemId()).orElse(null);
		if(pi == null)
			throw new OperationException(StandardConstant.ERROR_CANT_FIND_PAYMENT_IMAGE, null);
		payImgRepo.delete(pi);
	    return pi;
    }
	
	
	public SalesInvoice createPayment(
    		@Valid @NotNull(message="{error_no_payment}") Payment _payment) 
    {
		SalesInvoice si = soClient.getSalesOrder(_payment.getSoId());
		List<SalesInvoiceItem> items = new LinkedList<>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if(si == null)
			throw new OperationException(StandardConstant.ERROR_CANT_FIND_SALES_ORDER, null);
		
		si.setInvoiceNo(sdf.format(Calendar.getInstance().getTime()));
		si.setNoFakPajak(_payment.getNoFakPajak());
		si.setPaymentAmount(_payment.getPaymentAmount());
		si.setPaymentDetail(_payment.getPaymentDetail());
		si.setPaymentType(_payment.getPaymentType());
		
		
		// untuk relasi one to many yang cascade persist, kalau relasi tersebut meiliki relasi
		// lain lagi, maka objek relasi tersebut harus objek yang dimanage JPA
		if(si.getItems() != null && si.getItems().size() > 0) {
        	for(SalesInvoiceItem sItem : si.getItems()) {
        		sItem.setParent(si);
        	}
        }	
		
		SalesInvoice deletedSi = soClient.deleteSalesOrder(_payment.getSoId());
		
		if(deletedSi == null)
			throw new OperationException(StandardConstant.ERROR_CANT_FIND_SALES_ORDER, null) ;
		
		siRepo.save(si);
		
		if(_payment.getImageData() != null) {
			PaymentImage pi = new PaymentImage();
			pi.setSalesInvoice(si);
			pi.setName("" + si.getSystemId());
			pi.setImageData(Base64.getDecoder ().decode (_payment.getImageData()));
			addPaymentImage(pi);
		}
		
//		if(_pi != null)
//			addPaymentImage(_pi);
		return si;
    }
    
	public SalesInvoice editPayment(
    		@Valid @NotNull(message="{error_no_payment}") Payment _payment) 
    {
		SalesInvoice si = findSalesInvoiceByNo(_payment.getInvoiceNo());
		if(si == null)
			throw new OperationException(StandardConstant.ERROR_CANT_FIND_INVOICE, null);
		
		si.setNoFakPajak(_payment.getNoFakPajak());
		si.setInvoiceNo(_payment.getInvoiceNo());
		si.setPaymentAmount(_payment.getPaymentAmount());
		si.setPaymentDetail(_payment.getPaymentDetail());
		si.setPaymentType(_payment.getPaymentType());
		if(_payment.getImageData() != null) {
			PaymentImage pi = new PaymentImage();
			pi.setSalesInvoice(si);
			pi.setName("" + si.getSystemId());
			pi.setImageData(Base64.getDecoder ().decode (_payment.getImageData()));
			editPaymentImage(pi);
		}
		else {
			PaymentImage pi = findPaymentImage(si);
			if(pi != null)
				deletePaymentImage(si);
		}
		return si;
    }
		
	public SalesInvoice findSalesInvoice(@NotNull(message="{error_no_id}")Long _id) {
		return siRepo.findById(_id).orElse(null);
	}
	
	public SalesInvoice findSalesInvoiceByNo(String _no) {
		return siRepo.findByInvoiceNo(_no);
	}
	
	public Page<SalesInvoice> findAllSalesInvoice(Pageable _page) {
		return siRepo.findAll(_page);
	}
	
	public Page<SalesInvoice> findSalesInvoicesByCustId(Long _id, Pageable _page) {
		return siRepo.findByIdCustomer(_id, _page);
	}
	
	public SalesInvoice deleteSalesInvoice(@NotNull(message="{error_no_id}") Long _id) {
    	SalesInvoice si = siRepo.findById(_id).orElse(null);
		if(si == null)
			throw new OperationException(StandardConstant.ERROR_CANT_FIND_INVOICE, null);
	    siRepo.delete(si);
	    return si;
    }
	
	public SalesInvoiceItem findSalesInvoiceItem(@NotNull(message="{error_no_id}")SalesInvoiceItemPK _pk) {
		return siItemRepo.findById(_pk).orElse(null);
	}
	
	public Page<SalesInvoiceItem> findSalesInvoiceItemByParent(SalesInvoice _si, Pageable _page) {
		return siItemRepo.findByParent(_si, _page);
	}
	
	public PaymentImage findPaymentImage(@NotNull(message="{error_no_si}")SalesInvoice _si) {
		return payImgRepo.findById(_si.getSystemId()).orElse(null);
	}
    
    public PaymentImage findPaymentImageByInvoiceId(@NotNull(message="{error_no_id}")Long _id) {
		return payImgRepo.findBySalesInvoice_SystemId(_id);
	}
	
	private void preparePaymentImage(PaymentImage _pi) {
    	SalesInvoice si = findSalesInvoice(_pi.getSalesInvoice().getSystemId());
    	if(si == null)
    		throw new OperationException(StandardConstant.ERROR_CANT_FIND_INVOICE, null);
    	
    	_pi.setSalesInvoice(si);
    }

}
