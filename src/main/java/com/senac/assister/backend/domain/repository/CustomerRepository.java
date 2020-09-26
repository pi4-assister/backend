package com.senac.assister.backend.domain.repository;

import com.senac.assister.backend.domain.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, UUID> {
    Optional<Customer> findByEmailAndActiveTrue(String email);
    Optional<Customer> findByEmailAndPasswordAndActiveTrue(String email, String password);
    Optional<Customer> findByIdAndActiveTrue(UUID id);
}
