package com.aiv.crud.salesmanapi.repository;

import com.aiv.crud.salesmanapi.domain.entity.Salesman;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SalesmanRepository extends JpaRepository<Salesman, Long> {

    Optional<Salesman> findByRegistration(String registration);

}
