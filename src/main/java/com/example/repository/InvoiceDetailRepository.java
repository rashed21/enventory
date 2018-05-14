package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Color;
import com.example.model.Group;
import com.example.model.Invoice;
import com.example.model.InvoiceDetail;

@Repository("invoiceDetilRepository")
public interface InvoiceDetailRepository extends JpaRepository<InvoiceDetail, Integer> {
}
