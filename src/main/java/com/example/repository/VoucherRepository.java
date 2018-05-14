package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Voucher;

@Repository("voucherRepository")
public interface VoucherRepository extends JpaRepository<Voucher, Integer> {
}
