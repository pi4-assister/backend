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

    @ManyToOne()
    @JoinColumn(name = "customer_client_id")
    private Customer clientCustomer;

    @ManyToOne()
    @JoinColumn(name = "customer_partner_id")
    private Customer partnerCustomer;

    @OneToOne()
    @JoinColumn(name = "service_type_id")
    private ServiceType serviceType;

    @Column(name = "start_date")
    private Instant startDate;

    @Column(name = "final_date")
    private Instant finalDate;

    @Column(name = "description")
    private String description;

    @Column(name = "final_price")
    private double finalPrice;

    @OneToOne(mappedBy = "service")
    private Charge charge;

    @OneToOne
    @JoinColumn(name = "rate_id")
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


    public Service(UUID id, Customer clientCustomer, Customer partnerCustomer, ServiceType serviceType, Instant startDate, Instant finalDate, String description, double finalPrice, Charge charge, Rate rate, ServiceStatus serviceStatus, Instant createdAt, Instant updatedAt) {
        this.id = id;
        this.clientCustomer = clientCustomer;
        this.partnerCustomer = partnerCustomer;
        this.serviceType = serviceType;
        this.startDate = startDate;
        this.finalDate = finalDate;
        this.description = description;
        this.finalPrice = finalPrice;
        this.charge = charge;
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

    public Instant getStartDate() {
        return startDate;
    }

    public void setStartDate(Instant startDate) {
        this.startDate = startDate;
    }

    public Instant getFinalDate() {
        return finalDate;
    }

    public void setFinalDate(Instant finalDate) {
        this.finalDate = finalDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(double finalPrice) {
        this.finalPrice = finalPrice;
    }

    public Charge getCharge() {
        return charge;
    }

    public void setCharge(Charge charge) {
        this.charge = charge;
    }

    public Rate getRate() {
        return rate;
    }

    public void setRate(Rate rate) {
        this.rate = rate;
    }

    public ServiceStatus getServiceStatus() {
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