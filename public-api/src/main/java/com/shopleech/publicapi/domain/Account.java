package com.shopleech.publicapi.domain;

import com.shopleech.base.config.type.StatusTypeCode;
import com.shopleech.base.domain.BaseDomainEntityMetaId;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.util.Set;

/**
 * @author Ahto Jalak
 * @since 24.01.2023
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "_account")
public class Account extends BaseDomainEntityMetaId {

    @Id
    @GeneratedValue
    private Integer id;

    @ManyToMany
    @JoinTable(name = "_customer_account",
            joinColumns = @JoinColumn(name = "account_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "customer_id", referencedColumnName = "id")
    )
    private Set<Customer> customers;

    private StatusTypeCode status;

    private Timestamp validFrom;
    private Timestamp validTo;
    private Timestamp createdAt;
    private String createdBy;
    private Timestamp updatedAt;
    private String updatedBy;
}
