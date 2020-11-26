package com.senac.assister.backend.domain.repository;

import com.senac.assister.backend.domain.entity.Rate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface RateRepository extends JpaRepository<Rate, UUID> {

    @Query(value = "SELECT BIN_TO_UUID(s.customer_assister_id), SUM(r.score) AS rateNote FROM service s" +
            " INNER JOIN rate r ON r.id = s.rate_id" +
            " GROUP BY s.customer_assister_id", nativeQuery = true)
    List<Object[]> findAllRateNotes();
}
