package com.senac.assister.backend.domain.entity;

import com.senac.assister.backend.domain.enumeration.ServiceStatus;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "service")
public class Service {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_client_id")
    private Customer clientCustomer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_partner_id")
    private Customer partnerCustomer;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "service_type_id", nullable = false)
    private ServiceType serviceType;

    @Column(name = "service_date")
    private Instant serviceDate;

    @Column(name = "description")
    private String description;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "rate_id", nullable = false)
    private Rate rate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private ServiceStatus serviceStatus;

    @Generated(GenerationTime.INSERT)
    @Column(name = "created_at")
    private Instant createdAt;

    @Generated(GenerationTime.ALWAYS)
    @Column(name = "updated_at")
    private Instant updatedAt;

    public Service() {
    }

    public Service(UUID id, Customer clientCustomer, Customer partnerCustomer, ServiceType serviceType, Instant serviceDate, String description, Rate rate, ServiceStatus serviceStatus, Instant createdAt, Instant updatedAt) {
        this.id = id;
        this.clientCustomer = clientCustomer;
        this.partnerCustomer = partnerCustomer;
        this.serviceType = serviceType;
        this.serviceDate = serviceDate;
        this.description = description;
        this.rate = rate;
        this.serviceStatus = serviceStatus;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Customer getClientCustomer() {
        return clientCustomer;
    }

    public void setClientCustomer(Customer clientCustomer) {
        this.clientCustomer = clientCustomer;
    }

    public Customer getPartnerCustomer() {
        return partnerCustomer;
    }

    public void setPartnerCustomer(Customer partnerCustomer) {
        this.partnerCustomer = partnerCustomer;
    }

    public ServiceType getServiceType() {
        return serviceType;
    }

    public void setServiceType(ServiceType serviceType) {
        this.serviceType = serviceType;
    }

    public Instant getServiceDate() {
        return serviceDate;
    }

    public void setServiceDate(Instant serviceDate) {
        this.serviceDate = serviceDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Rate getRate() {
        return rate;
    }

    public void setRate(Rate rate) {
        this.rate = rate;
    }

    public Object getServiceStatus() {
        return serviceStatus;
    }

    public void setServiceStatus(ServiceStatus serviceStatus) {
        this.serviceStatus = serviceStatus;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }
}