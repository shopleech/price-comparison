package com.shopleech.publicapi.domain;

import com.shopleech.base.config.type.StatusTypeCode;
import com.shopleech.base.domain.BaseDomainEntityMetaId;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.util.HashSet;
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
@Table(name = "account")
public class Account extends BaseDomainEntityMetaId {

    @Id
    @GeneratedValue
    private Integer id;

    private StatusTypeCode status;

    private Timestamp validFrom;
    private Timestamp validTo;
    private Timestamp createdAt;
    private String createdBy;
    private Timestamp updatedAt;
    private String updatedBy;

    @OneToMany(mappedBy = "account")
    Set<CustomerAccount> customerAccounts = new HashSet<>();

    @OneToMany(mappedBy = "account")
    private Set<Offer> offers = new HashSet<>();
}
