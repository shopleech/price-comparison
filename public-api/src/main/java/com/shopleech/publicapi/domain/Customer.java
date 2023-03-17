package com.shopleech.publicapi.domain;

import com.shopleech.base.config.type.StatusTypeCode;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
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
@Table(name = "_customer")
public class Customer {

    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne
    private User user;

    @ManyToMany
    @JoinTable(name = "_customer_account",
            joinColumns = @JoinColumn(name = "customer_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "account_id", referencedColumnName = "id")
    )
    private Set<Account> accounts;

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

    @OneToMany
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Set<Watchlist> watchlists;

    @OneToMany
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Set<Alarm> alarms;

    @OneToMany
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Set<Review> reviews;
}
