package com.shopleech.publicapi.domain;

import com.shopleech.base.config.type.FeatureTypeCode;
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
@Table(name = "feature")
public class Feature {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "feature_generator")
    @SequenceGenerator(name = "feature_generator", sequenceName = "feature_seq", allocationSize = 1)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "offer_id", nullable = false)
    private Offer offer;

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
