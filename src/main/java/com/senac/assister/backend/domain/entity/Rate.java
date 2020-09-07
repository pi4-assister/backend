package com.senac.assister.backend.domain.entity;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "rate")
public class Rate {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @OneToOne(mappedBy = "rate")
    private Service service;

    @Column(name = "score")
    private double score;

    @Column(name = "description")
    private String description;

    @Column(name = "active")
    private boolean active = true;

    @Generated(GenerationTime.INSERT)
    @Column(name = "created_at")
    private Instant createdAt;

    @Generated(GenerationTime.ALWAYS)
    @Column(name = "updated_at")
    private Instant updatedAt;

    public Rate() {
    }

    public Rate(UUID id, Service service, double score, String description, boolean active, Instant createdAt, Instant updatedAt) {
        this.id = id;
        this.service = service;
        this.score = score;
        this.description = description;
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

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
