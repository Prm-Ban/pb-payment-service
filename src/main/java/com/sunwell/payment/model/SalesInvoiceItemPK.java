/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * ItemPOPK.java
 *
 * Created on Apr 17, 2015, 11:50:33 AM
 */

package com.sunwell.payment.model;

import java.io.Serializable;

/**
 *
 * @author Benny
 */

public class SalesInvoiceItemPK implements Serializable
{
    long parent ;
    int idItem ;
    
   

    public long getParent ()
    {
        return parent;
    }

    public void setParent (long _so)
    {
        this.parent = _so;
    }  
    
    public int getIdItem ()
    {
        return idItem;
    }

    public void setIdItem (int _item)
    {
        this.idItem = _item;
    } 
    
    @Override
    public int hashCode ()
    {
        int hash = 0;
//        hash += salesOrder != null ? salesOrder.hashCode() : 0;
//        hash += item != null ? item.hashCode() : 0 ;
        hash += parent ;
        hash += idItem ;
        return hash;
    }

    @Override
    public boolean equals (Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
    	if(object == null)
    		return false;
        if (!(object instanceof SalesInvoiceItemPK)) {
            return false;
        }
        SalesInvoiceItemPK other = (SalesInvoiceItemPK) object;
        
        if(parent != other.parent)
        	return false;
        
        if(idItem != other.idItem)
        	return false;
       
        return true;
    }
}