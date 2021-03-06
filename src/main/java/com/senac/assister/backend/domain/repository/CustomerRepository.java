package com.senac.assister.backend.domain.repository;

import com.senac.assister.backend.domain.entity.Customer;
import com.senac.assister.backend.domain.enumeration.CustomerStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, UUID> {
    Optional<Customer> findByEmailAndStatusNot(String email, CustomerStatus status);

    Optional<Customer> findByIdAndStatusNot(UUID id, CustomerStatus status);


    @Query(value = "SELECT c FROM Customer c LEFT JOIN Service s ON s.assisterCustomer = c.id " +
            "WHERE c.customerType = 'ASSISTER' AND ( " +
            "( " +
                "(s.startDate NOT BETWEEN :startDate AND :finalDate) " +
                "AND " +
                "(s.finalDate NOT BETWEEN :startDate AND :finalDate) " +
            ") " +
            "OR " +
            "(s.serviceStatus = 'FINISHED' OR s.serviceStatus = 'CANCELED') " +
            "OR " +
            "s.id IS NULL ) ")
    List<Customer> findAssistersInRange(
            @Param("startDate") Instant startDate,
            @Param("finalDate") Instant finalDate
    );

    @Query(value = "SELECT BIN_TO_UUID(c.id), COUNT(s.customer_assister_id) AS qtdService FROM customer c " +
            "LEFT JOIN service s ON s.customer_assister_id = c.id " +
            "GROUP BY s.customer_assister_id, c.id", nativeQuery = true)
    List<Object[]> findAllAmountServices();

}
