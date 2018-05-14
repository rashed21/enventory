package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.BrandType;

@Repository("brandTypeRepository")
public interface BrandTypeRepository extends JpaRepository<BrandType, Integer> {
}
