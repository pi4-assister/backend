package com.senac.assister.backend.domain.repository;

import com.senac.assister.backend.domain.entity.Customer;
import com.senac.assister.backend.domain.entity.Service;
import com.senac.assister.backend.domain.enumeration.ServiceStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ServiceRepository extends JpaRepository<Service, UUID> {
    List<Service> findAllByServiceStatus(ServiceStatus status);

    List<Service> findAllByClientCustomerIdOrAssisterCustomerIdAndServiceStatus(UUID clientCustomerId, UUID assisterCustomerId, ServiceStatus status);
}
