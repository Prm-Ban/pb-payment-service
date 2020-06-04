package com.sunwell.payment.model;

import java.util.Base64;


public class Payment 
{
	private long soId;
	private String invoiceNo;
    private Integer paymentType;
    private String paymentDetail;
    private Double paymentAmount;
    private String noFakPajak;
    private String imageData;
    
    public long getSoId ()
	{
		return soId;
	}
	public void setSoId (long _id)
	{
		soId = _id;
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
	public String getImageData ()
	{
		return imageData;
	}
	public void setImageData (String _imageData)
	{
		imageData = _imageData;
	}
}
