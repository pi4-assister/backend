package com.senac.assister.backend.domain.entity;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "special_needs")
public class SpecialNeeds {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @ManyToOne()
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToOne()
    @JoinColumn(name = "special_need_type_id")
    private SpecialNeedType specialNeedType;

    @Column(name = "active")
    private boolean active;

    @Generated(GenerationTime.INSERT)
    @Column(name = "created_at")
    private Instant createdAt;

    @Generated(GenerationTime.ALWAYS)
    @Column(name = "updated_at")
    private Instant updatedAt;

    public SpecialNeeds() {
    }

    public SpecialNeeds(UUID id, Customer customer, SpecialNeedType specialNeedType, boolean active, Instant createdAt, Instant updatedAt) {
        this.id = id;
        this.customer = customer;
        this.specialNeedType = specialNeedType;
        this.active = active;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public SpecialNeedType getSpecialNeedType() {
        return specialNeedType;
    }

    public void setSpecialNeedType(SpecialNeedType specialNeedType) {
        this.specialNeedType = specialNeedType;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
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
