package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Voucher;
import com.example.model.Wholeseller;

@Repository("wholesellerRepository")
public interface WholesellerRepository extends JpaRepository<Wholeseller, Integer> {
}
