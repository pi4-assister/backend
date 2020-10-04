package com.senac.assister.backend.domain.entity;

import com.senac.assister.backend.domain.enumeration.CustomerStatus;
import com.senac.assister.backend.domain.enumeration.CustomerType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "customer")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @Column(name = "photo_url")
    private String photoUrl;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "person_identifier")
    private String personIdentifier;

    @Column(name = "is_legal_person")
    private boolean isLegalPerson;

    @Column(name = "bio")
    private String bio;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "customer_type")
    private CustomerType customerType;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private CustomerStatus status;

    @Column(name = "landline_number")
    private String landlineNumber;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "birthdate")
    private Instant birthdate;

    @Column(name = "address")
    private String address;

    @OneToMany(mappedBy = "role")
    @JoinTable(
                name = "user_roles",
                joinColumns = @JoinColumn(name = "customer_id"),
                inverseJoinColumns =  @JoinColumn(name ="role_id")
              )
    private List<Role> roles;

//    @JsonIgnore
//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
//    private List<CreditCard> creditCards;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @Column(name = "zip_code")
    private String zipCode;

    @Column(name = "active")
    private boolean active = true;

    @Generated(GenerationTime.INSERT)
    @Column(name = "created_at")
    private Instant createdAt;

    @Generated(GenerationTime.ALWAYS)
    @Column(name = "updated_at")
    private Instant updatedAt;

    public Customer(UUID id, String fullName, String personIdentifier, boolean isLegalPerson, String bio, String phoneNumber, CustomerType customerType, CustomerStatus status, String landlineNumber, String email, String password, Instant birthdate, String address, String city, String state, String zipCode, boolean active, Instant createdAt, Instant updatedAt, List<Role> roles) {
        this.id = id;
        this.fullName = fullName;
        this.personIdentifier = personIdentifier;
        this.isLegalPerson = isLegalPerson;
        this.bio = bio;
        this.phoneNumber = phoneNumber;
        this.customerType = customerType;
        this.status = status;
        this.landlineNumber = landlineNumber;
        this.email = email;
        this.password = password;
        this.birthdate = birthdate;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.active = active;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.roles = roles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return email.equals(customer.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }
}