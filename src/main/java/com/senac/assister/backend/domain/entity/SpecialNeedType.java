package com.senac.assister.backend.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.senac.assister.backend.domain.enumeration.SpecialNeedTypes;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "special_needs_type")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SpecialNeedType {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @JsonIgnore
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "active")
    @JsonIgnore
    private boolean active;

    @Column(name = "price")
    private double price;

    @Generated(GenerationTime.INSERT)
    @Column(name = "created_at")
    @JsonIgnore
    private Instant createdAt;

    @Generated(GenerationTime.ALWAYS)
    @Column(name = "updated_at")
    @JsonIgnore
    private Instant updatedAt;
}
