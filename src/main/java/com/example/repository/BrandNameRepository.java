package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.BrandName;

@Repository("brandNameRepository")
public interface BrandNameRepository extends JpaRepository<BrandName, Integer> {
}
