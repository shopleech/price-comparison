package com.shopleech.publicapi.domain;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

/**
 * @author Ahto Jalak
 * @since 05.02.2023
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "customer_account")
public class CustomerAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_account_generator")
    @SequenceGenerator(name = "customer_account_generator", sequenceName = "customer_account_seq", allocationSize = 1)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    private Timestamp validFrom;
    private Timestamp validTo;
    private Timestamp createdAt;
    private String createdBy;
    private Timestamp updatedAt;
    private String updatedBy;
}
