/*
 * SOLines.java
 *
 * Created on September 22, 2007, 7:34 PM
 */
package com.sunwell.payment.model;

import java.sql.*;


import java.util.Arrays;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotNull;




/**
 *
 * @author Irfin A
 * @author Benny
 * 
 * @version 1.0 - Sep 22, 2007 ; initial version
 * @version 1.5 - Mar 13, 2010 ; perubahan interface dari PersistentObject ke
                  EntityObject
 * @version 2.0 - May 24, 2015 ; tambahan atribut m_metricUsed dan m_qtyInMetricUsed
 */
@Entity
@Table( name = "salesinvoiceitem" )
@IdClass(SalesInvoiceItemPK.class)
public class SalesInvoiceItem 
{
    @NotNull(message="{error_no_si}")
    @Id
    @ManyToOne
    @JoinColumn( name ="parent")
    private SalesInvoice parent;
    
    @NotNull(message="{error_no_id_item}")
    @Id
    @JoinColumn( name = "id_item")
    private int idItem ;
    
    @NotNull(message="{error_no_item_name}")
    @Column(name="item_name")
    private String itemName ;
    
    @Column( name = "qty" )
    private double qty = 0.0;
    
    @Column( name = "disc" )
    private double discount = 0.0;
    
    @Column( name = "harga_jual" )
    private double hargaJual = 0.0;
    
    @Column(name="reqnote")
    private String reqNote;
    
    
    
    
    /** Creates a new instance of SOItem */
    public SalesInvoiceItem ()
    {
//    	parent = null;
//        item = null;
//        shipmentUsed = null ;
//        metricUsed = null ;
//        qty = 0.0 ;
//        qtyInMetricUsed = 0.0 ;
    }
    
//    /** Creates a new instance of exist SOItem */
//    public SalesInvoiceItem (SalesInvoice _si, Item _item)
//    {
//        parent = _si;
//        item = _item;
//        shipmentUsed = null ;
//        qty = 0.0 ;
//        qtyInMetricUsed = 0.0 ;
//    }
    
//    public SalesOrder getSalesOrder () { return salesOrder; }
//
//    public void setItem (Item _item)
//    {
//        this.item = _item;
//    }
    
    public SalesInvoice getParent () { return parent; }

    public void setParent (SalesInvoice m_so)
    {
        this.parent = m_so;
    }
        
    public int getIdItem () { return idItem; }

    public void setIdItem(int idItem) {
		this.idItem = idItem;
	}  

    public double getQty () { return qty; }

    /**
     * Penggunaan method ini akan meng-override nilai qty yang sebelumnya diset
     * melalui {@link #setQtyInSalesMetric(double, sunwell.xrp.inventory.Metrics)}.
     * 
     * @param m_qty 
     */
    public void setQty (double _qty)
    {
        this.qty = _qty;
    }
    

    public double getHargaJual () { return hargaJual; }

    public void setHargaJual (double m_harga_jual)
    {
        this.hargaJual = m_harga_jual;
    }
        

	public double getDiscount ()
	{
		return discount;
	}

	public void setDiscount (double _discount)
	{
		discount = _discount;
	}

	public String getReqNote ()
	{
		return reqNote;
	}

	public void setReqNote (String _reqNote)
	{
		reqNote = _reqNote;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	

	 
}
