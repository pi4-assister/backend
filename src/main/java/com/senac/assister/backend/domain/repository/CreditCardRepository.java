package com.senac.assister.backend.domain.repository;

import com.senac.assister.backend.domain.entity.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CreditCardRepository extends JpaRepository<CreditCard, UUID> {

    CreditCard findTopByOrderByCreatedAtDesc();

    List<CreditCard> findAllByCustomerIdAndActiveTrue(UUID id);
}
