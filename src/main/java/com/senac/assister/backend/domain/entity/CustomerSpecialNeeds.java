package com.senac.assister.backend.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "customer_special_needs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerSpecialNeeds {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    @JsonIgnore
    private Customer customer;

    @ManyToOne()
    @JoinColumn(name = "special_needs_type_id")
    private SpecialNeedType specialNeedType;

    @Column(name = "active")
    @JsonIgnore
    private boolean active;

    @Generated(GenerationTime.INSERT)
    @Column(name = "created_at")
    @JsonIgnore
    private Instant createdAt;

    @Generated(GenerationTime.ALWAYS)
    @Column(name = "updated_at")
    @JsonIgnore
    private Instant updatedAt;
}
