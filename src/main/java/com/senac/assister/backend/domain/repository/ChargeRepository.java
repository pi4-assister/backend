package com.senac.assister.backend.domain.repository;

import com.senac.assister.backend.domain.entity.Charge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ChargeRepository extends JpaRepository<Charge, UUID> {
}
