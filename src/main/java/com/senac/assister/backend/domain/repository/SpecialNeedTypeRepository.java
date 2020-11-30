package com.senac.assister.backend.domain.repository;

import com.senac.assister.backend.domain.entity.Customer;
import com.senac.assister.backend.domain.entity.SpecialNeedType;
import com.senac.assister.backend.domain.enumeration.CustomerStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface SpecialNeedTypeRepository extends JpaRepository<SpecialNeedType, UUID> {
    Optional<SpecialNeedType> findByName(String name);

}
