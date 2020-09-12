package com.senac.assister.backend.domain.repository;

import com.senac.assister.backend.domain.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, UUID> {
    Optional<Customer> findByEmailAndActiveTrue(String email);
}
