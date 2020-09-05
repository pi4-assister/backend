package com.senac.assister.backend.domain.repository;

import com.senac.assister.backend.domain.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CustomerRepository extends JpaRepository<Customer, UUID> {
}
