package com.sunwell.payment.repository;

import java.util.List;




import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.sunwell.payment.model.SalesInvoice;
import com.sunwell.payment.model.SalesInvoiceItem;
import com.sunwell.payment.model.SalesInvoiceItemPK;


public interface SalesInvoiceItemRepo extends JpaRepository<SalesInvoiceItem, SalesInvoiceItemPK> {
	Page<SalesInvoiceItem> findByParent(SalesInvoice _si, Pageable _page);
}
