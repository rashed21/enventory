package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Color;
import com.example.model.Group;
import com.example.model.Invoice;

@Repository("invoiceRepository")
public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {
}
