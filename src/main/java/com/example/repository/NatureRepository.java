package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Color;
import com.example.model.Group;
import com.example.model.Invoice;
import com.example.model.Model;
import com.example.model.Nature;

@Repository("natureRepository")
public interface NatureRepository extends JpaRepository<Nature, Integer> {
}
