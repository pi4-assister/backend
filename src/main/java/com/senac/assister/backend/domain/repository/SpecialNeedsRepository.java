package com.senac.assister.backend.domain.repository;

import com.senac.assister.backend.domain.entity.CustomerSpecialNeeds;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SpecialNeedsRepository extends JpaRepository<CustomerSpecialNeeds, UUID> {
}
