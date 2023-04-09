package com.shopleech.publicapi.domain;

import com.shopleech.base.config.type.StatusTypeCode;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Ahto Jalak
 * @since 05.02.2023
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue
    private Integer id;

    private String personalCode;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private StatusTypeCode status;

    private Timestamp validFrom;
    private Timestamp validTo;
    private Timestamp createdAt;
    private String createdBy;
    private Timestamp updatedAt;
    private String updatedBy;

    @OneToMany(mappedBy = "customer")
    private Set<User> users = new HashSet<>();

    @OneToMany(mappedBy = "customer")
    private Set<CustomerAccount> customerAccounts = new HashSet<>();

    @OneToMany(mappedBy = "customer")
    private Set<Watchlist> watchlists = new HashSet<>();

    @OneToMany(mappedBy = "customer")
    private Set<Alarm> alarms = new HashSet<>();

    @OneToMany(mappedBy = "customer")
    private Set<Review> reviews = new HashSet<>();
}
