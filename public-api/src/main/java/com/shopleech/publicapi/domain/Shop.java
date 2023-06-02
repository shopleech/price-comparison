package com.shopleech.publicapi.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Ahto Jalak
 * @since 25.03.2023
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "shop")
public class Shop {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "shop_generator")
    @SequenceGenerator(name = "shop_generator", sequenceName = "shop_seq", allocationSize = 1)
    private Integer id;

    private String name;
    private String address;
    private String url;
    private Double latitude;
    private Double longitude;

    private Timestamp validFrom;
    private Timestamp validTo;
    private Timestamp createdAt;
    private String createdBy;
    private Timestamp updatedAt;
    private String updatedBy;

    @OneToMany(mappedBy = "shop")
    private Set<Offer> offers = new HashSet<>();
}
