package com.shopleech.publicapi.domain;

import com.shopleech.base.config.FeatureTypeCode;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

/**
 * @author Ahto Jalak
 * @since 06.02.2023
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "_feature")
public class Feature {

    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne
    private AccountProduct accountProduct;

    private FeatureTypeCode featureTypeCode;
    private String name;
    private String description;

    private Timestamp validFrom;
    private Timestamp validTo;
    private Timestamp createdAt;
    private String createdBy;
    private Timestamp updatedAt;
    private String updatedBy;
}
