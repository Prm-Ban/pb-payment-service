/*
 * SalesOrder.java
 *
 * Created on September 4, 2007, 5:28 PM
 */
package com.sunwell.payment.dto;

import java.io.Serializable;

import java.sql.*;
import java.util.*;

import javax.validation.Validation;
import javax.validation.constraints.NotNull;

import com.sunwell.payment.model.SalesInvoice;
import com.sunwell.payment.model.SalesInvoiceItem;

public class SalesInvoiceDTO extends StandardDTO
{
    private Long systemId;
    private Long idCustomer;
    private List<SalesInvoiceItemDTO> items ;
    private Calendar issueDate = Calendar.getInstance();
    private Integer deliveryStatus ;
    private Double miscCharge = 0.0;
    private String miscChargeMemo = "";
    private String memo = "";
    private Double vat = 0.0;
    private Boolean vatInclusive = false;
    private Integer canceledStatus = 0;
    private Integer paymentStatus ;
    private Integer shippingLine ;
    private Integer promoCodeUsed ;
    private Double discount;
    private String discountMemo;
    private String invoiceNo;
    private Integer paymentType;
    private String paymentDetail;
    private Double paymentAmount;
    private String noFakPajak;
        
    /** Creates a new instance of SalesOrder */
    public SalesInvoiceDTO ()
    {
    }
    
    public SalesInvoiceDTO(SalesInvoice _so) {
    	setData(_so);
    }
    
    public void setData(SalesInvoice _si) {
    	systemId = _si.getSystemId();
    	invoiceNo = _si.getInvoiceNo();
    	noFakPajak = _si.getNoFakPajak();
    	paymentType = _si.getPaymentType();
    	paymentDetail = _si.getPaymentDetail();
    	paymentAmount = _si.getPaymentAmount();
    	issueDate = _si.getIssueDate();
    	deliveryStatus = _si.getDeliveryStatus();
    	miscCharge = _si.getMiscCharges();
    	miscChargeMemo = _si.getMiscChargesMemo();
    	memo = _si.getMemo();
    	vat = _si.getVAT();
    	vatInclusive = _si.isVATInclusive();
    	canceledStatus = _si.getCanceledStatus();
    	paymentStatus = _si.getPaymentStatus();
    	shippingLine = _si.getShippingLine();
    	promoCodeUsed = _si.getPromoCodeUsed();
    	discount = _si.getDiscount();
    	discountMemo = _si.getDiscountMemo();
    	idCustomer = _si.getIdCustomer();
    	
    	if(_si.getItems() != null && _si.getItems().size() > 0) {
    		items = new LinkedList<>();
    		for(SalesInvoiceItem sItem: _si.getItems()) {
    			items.add(new SalesInvoiceItemDTO(sItem));
    		}
    	}
    }
    
    public SalesInvoice getData() {
    	SalesInvoice si = new SalesInvoice();
    	
    	if(invoiceNo != null)
    		si.setInvoiceNo(invoiceNo);
    	if(noFakPajak != null)
    		si.setNoFakPajak(noFakPajak);
    	if(issueDate != null)
    		si.setIssueDate(issueDate);
    	if(deliveryStatus != null)
    		si.setDeliveryStatus(deliveryStatus);
    	if(miscChargeMemo != null)
    		si.setMiscChargesMemo(miscChargeMemo);
    	if(memo != null)
    		si.setMemo(memo);
    	if(discountMemo != null)
    		si.setDiscountMemo(discountMemo);
    	if(paymentDetail != null)
    		si.setPaymentDetail(paymentDetail);
    	if(systemId != null)
    		si.setSystemId(systemId);
    	if(miscCharge != null)
    		si.setMiscCharges(miscCharge);
    	if(paymentStatus != null)
    		si.setPaymentStatus(paymentStatus);
    	if(shippingLine != null)
    		si.setShippingLine(shippingLine);
    	if(promoCodeUsed != null)
    		si.setPromoCodeUsed(promoCodeUsed);
    	if(discount != null)
    		si.setDiscount(discount);
    	if(vat != null)
    		si.setVAT(vat);
    	if(vatInclusive != null)
    		si.setVATInclusive(vatInclusive);
    	if(canceledStatus != null)
    		si.setCanceledStatus(canceledStatus);
    	if(idCustomer != null)
    		si.setIdCustomer(idCustomer);
    	if(paymentType != null)
    		si.setPaymentType(paymentType);
    	if(paymentAmount != null)
    		si.setPaymentAmount(paymentAmount);
    	if(items != null && items.size() > 0) {
    		List<SalesInvoiceItem> listItem = new LinkedList<>();
    		for(SalesInvoiceItemDTO sItem : items) {
    			SalesInvoiceItem data = sItem.getData();
    			data.setParent(si);
    			listItem.add(data);
    		}
    		si.setItems(listItem);
    	}
    	return si;
    }

    
    public Long getSystemId () { return systemId; }
    
    public void setSystemId (Long _id)
    {
        this.systemId = _id;
    }

 
    public Calendar getIssueDate () { return issueDate; }

    public void setIssueDatetime (Calendar _date)
    {
        this.issueDate = _date;
    }

    public Integer getDeliveryStatus () { return deliveryStatus; }

    public void setDeliveredStatus (Integer _delivered)
    {
        this.deliveryStatus = _delivered;
    }

    
    public Double getMiscCharges () { return miscCharge; }

    public void setMiscCharges (Double m_misc_charges)
    {
        this.miscCharge = m_misc_charges;
    }

    public String getMiscChargesMemo () { return miscChargeMemo; }

    public void setMiscChargesMemo (String _misc_charges_memo)
    {
        this.miscChargeMemo = (_misc_charges_memo != null) ? _misc_charges_memo : "";
    }

    public String getMemo () { return memo; }

    public void setMemo (String _memo)
    {
        this.memo = (_memo != null) ? _memo : "";
    }

    public Double getVAT () { return vat; }

    public void setVAT (Double _vat)
    {
        this.vat = _vat;
    }

    public Boolean isVATInclusive () { return vatInclusive; }

    public void setVATInclusive (Boolean m_vat_inclusive)
    {
        this.vatInclusive = m_vat_inclusive;
    }

    public Integer getCanceledStatus () { return canceledStatus; }

    public void setCanceledStatus (Integer _vs)
    {
        canceledStatus = _vs;
    }
                    
    public Long getIdCustomer () { return idCustomer; }

    public void setIdCustomer (Long _cust)
    {
        this.idCustomer = _cust;
    }
  
    public List<SalesInvoiceItemDTO> getItems () 
    { 
        return items; 
    }
    
    public void setItems (List<SalesInvoiceItemDTO> _items)
    {
        items = _items;
    }

	public Integer getPaymentStatus ()
	{
		return paymentStatus;
	}

	public void setPaymentStatus (Integer _paymentStatus)
	{
		paymentStatus = _paymentStatus;
	}

	public Integer getShippingLine ()
	{
		return shippingLine;
	}

	public void setShippingLine (Integer _shippingLine)
	{
		shippingLine = _shippingLine;
	}

	public Integer getPromoCodeUsed ()
	{
		return promoCodeUsed;
	}

	public void setPromoCodeUsed (Integer _promoCodeUsed)
	{
		promoCodeUsed = _promoCodeUsed;
	}

	public Double getDiscount ()
	{
		return discount;
	}

	public void setDiscount (Double _discount)
	{
		discount = _discount;
	}

	public String getDiscountMemo ()
	{
		return discountMemo;
	}

	public void setDiscountMemo (String _discountMemo)
	{
		discountMemo = _discountMemo;
	}

	public String getInvoiceNo ()
	{
		return invoiceNo;
	}

	public void setInvoiceNo (String _noInvoice)
	{
		invoiceNo = _noInvoice;
	}

	public Integer getPaymentType ()
	{
		return paymentType;
	}

	public void setPaymentType (Integer _paymentType)
	{
		paymentType = _paymentType;
	}

	public String getPaymentDetail ()
	{
		return paymentDetail;
	}

	public void setPaymentDetail (String _paymentDetail)
	{
		paymentDetail = _paymentDetail;
	}

	public Double getPaymentAmount ()
	{
		return paymentAmount;
	}

	public void setPaymentAmount (Double _paymentAmount)
	{
		paymentAmount = _paymentAmount;
	}

	public String getNoFakPajak ()
	{
		return noFakPajak;
	}

	public void setNoFakPajak (String _noFakPajak)
	{
		noFakPajak = _noFakPajak;
	}

  
}
