package com.example.demo.repository;

import com.example.demo.entity.Corso;
import com.example.demo.entity.Discente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiscentiRepository extends JpaRepository<Discente, Integer> {
}
