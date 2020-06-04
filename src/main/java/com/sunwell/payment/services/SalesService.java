package com.sunwell.payment.services;


import java.lang.reflect.Field;

import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
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

import com.sunwell.payment.model.Payment;
import com.sunwell.payment.model.SalesInvoice;
import com.sunwell.payment.model.SalesInvoiceItem;
import com.sunwell.payment.model.SalesInvoiceItemPK;



public interface SalesService
{
	public SalesInvoice createPayment(@Valid @NotNull(message="{error_no_payment}")Payment _payment) throws Exception ;
	public SalesInvoice editPayment(@Valid @NotNull(message="{error_no_payment}") Payment _payment) throws Exception ;
	public SalesInvoice findSalesInvoice(@NotNull(message="{error_no_id}") Long _id) throws Exception ;
	public SalesInvoice findSalesInvoiceByNo(String _no) throws Exception ;
	public Page<SalesInvoice> findAllSalesInvoice(Pageable _page) throws Exception ;
	public Page<SalesInvoice> findSalesInvoicesByCustId(Long _id, Pageable _page) throws Exception ;
	public SalesInvoice deleteSalesInvoice(@NotNull(message="{error_no_id}") Long _id) throws Exception ;
	public SalesInvoiceItem findSalesInvoiceItem(@NotNull(message="{error_no_id}")SalesInvoiceItemPK _pk) throws Exception ;
	public Page<SalesInvoiceItem> findSalesInvoiceItemByParent(SalesInvoice _si, Pageable _page) throws Exception ;
	
}
