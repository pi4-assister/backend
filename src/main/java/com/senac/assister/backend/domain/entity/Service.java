package com.senac.assister.backend.domain.entity;

import com.senac.assister.backend.domain.enumeration.ServiceStatus;
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
@Table(name = "service")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
}