/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * ProdCategoryListDTO.java
 *
 * Created on Oct 18, 2017, 3:27:27 PM
 */

package com.sunwell.payment.dto;

import java.util.LinkedList;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.sunwell.payment.model.SalesInvoice;


/**
 *
 * @author Benny
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class SalesInvoiceListDTO extends StandardDTO
{
    
    private List<SalesInvoiceDTO> listSalesInvoice;
    
    public SalesInvoiceListDTO() {
        
    }
    
    public SalesInvoiceListDTO(List<SalesInvoice> _orders) {
        setData (_orders);
    }
    
    public void setData(List<SalesInvoice> _salesInvoices) {
        if(_salesInvoices != null && _salesInvoices.size () > 0) {
        	listSalesInvoice = new LinkedList<> ();
            for (SalesInvoice si : _salesInvoices) {
                listSalesInvoice.add (new SalesInvoiceDTO (si));
            }
        }
        else
            listSalesInvoice = null;
    }

    /**
     * @return the listUser
     */
    public List<SalesInvoiceDTO> getListSalesInvoice ()
    {
        return listSalesInvoice;
    }

    /**
     * @param listCategory the listCategory to set
     */
    public void setListSalesInvoice (List<SalesInvoiceDTO> _list)
    {
        this.listSalesInvoice = _list;
    }
}
