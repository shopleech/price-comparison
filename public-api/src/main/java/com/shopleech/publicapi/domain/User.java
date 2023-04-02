package com.shopleech.publicapi.domain;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Ahto Jalak
 * @since 04.02.2023
 */
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "_user")
public class User {

    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = true)
    private Customer customer;

    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private boolean enabled;

    private Timestamp validFrom;
    private Timestamp validTo;
    private Timestamp createdAt;
    private String createdBy;
    private Timestamp updatedAt;
    private String updatedBy;

    @OneToMany(mappedBy = "user")
    private Set<UserRole> userRoles = new HashSet<>();
}
