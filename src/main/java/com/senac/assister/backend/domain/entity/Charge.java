package com.senac.assister.backend.domain.entity;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "charge")
public class Charge {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @Column(name = "customer_id")
    private Customer customer;

    @Column(name = "credit_card_id")
    private CreditCard creditCard;

    // Not created yet.
    @Column(name = "service_id")
    private Service service;

    // Should be ENUM! Needs to create.
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Object status;

    @Column(name = "amount")
    private double amount;

    @Generated(GenerationTime.INSERT)
    @Column(name = "created_at")
    private Instant createdAt;

    @Generated(GenerationTime.ALWAYS)
    @Column(name = "updated_at")
    private Instant updatedAt;
}
