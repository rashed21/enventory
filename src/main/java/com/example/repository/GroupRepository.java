package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Color;
import com.example.model.Group;

@Repository("groupRepository")
public interface GroupRepository extends JpaRepository<Group, Integer> {
}
