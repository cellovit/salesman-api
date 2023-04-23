package com.aiv.crud.salesmanapi.repository;

import com.aiv.crud.salesmanapi.domain.entity.Salesman;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalesmanRepository extends JpaRepository<Salesman, Long> {
}
