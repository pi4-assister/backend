package com.senac.assister.backend.domain.repository;

import com.senac.assister.backend.domain.entity.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CreditCardRepository extends JpaRepository<CreditCard, UUID> {

    Optional<CreditCard> findTopByOrderByCreatedAtDesc();

    Optional<CreditCard> findByIdAndActiveTrue(UUID id);

    List<CreditCard> findAllByCustomerIdAndActiveTrue(UUID id);
}
