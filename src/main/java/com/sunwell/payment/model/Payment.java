package com.sunwell.payment.model;

import java.util.Base64;


public class Payment 
{
//	private String soNo;
//	private int siId;
	private long soId;
	private String invoiceNo;
    private Integer paymentType;
    private String paymentDetail;
    private Double paymentAmount;
    private String noFakPajak;
    private String imageData;
    
//	public String getSoNo ()
//	{
//		return soNo;
//	}
//	public void setSoNo (String _soNo)
//	{
//		soNo = _soNo;
//	}
    
    public long getSoId ()
	{
		return soId;
	}
	public void setSoId (long _id)
	{
		soId = _id;
	}
	
//	public int getSiId ()
//	{
//		return soId;
//	}
//	public void setSiId (int _id)
//	{
//		soId = _id;
//	}
    
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
	
//	public PaymentImage getProductImage() {
//    	if(imageData != null) {
//    		System.out.println("Image data is not null");
//    		PaymentImage pi = new PaymentImage();
//    		pi.setProduct(p);
//    		pi.setName(p.getName());
//    		pi.setImageData(Base64.getDecoder ().decode (getImageData()));
//    		return pi;
//    	}
//    	else
//    		return null;
//    }
}
