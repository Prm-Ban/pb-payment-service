package com.sunwell.payment.repository;

import java.util.List;



import org.springframework.data.jpa.repository.JpaRepository;

import com.sunwell.payment.model.PaymentImage;
import com.sunwell.payment.model.SalesInvoice;



public interface PaymentImageRepo extends JpaRepository<PaymentImage, Long> {
	PaymentImage findBySalesInvoice(SalesInvoice _s);
	PaymentImage findBySalesInvoice_SystemId(Long _p);
}
