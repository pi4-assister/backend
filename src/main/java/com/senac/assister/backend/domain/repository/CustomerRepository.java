package com.senac.assister.backend.domain.repository;

import com.senac.assister.backend.domain.entity.Customer;
import com.senac.assister.backend.domain.enumeration.CustomerStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, UUID> {
    Optional<Customer> findByEmailAndStatusNot(String email, CustomerStatus status);

    Optional<Customer> findByIdAndStatusNot(UUID id, CustomerStatus status);
}
