package com.shopleech.publicapi.domain;

import com.shopleech.base.config.type.AlarmTypeCode;
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
@Table(name = "alarm")
public class Alarm {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "alarm_generator")
    @SequenceGenerator(name = "alarm_generator", sequenceName = "alarm_seq", allocationSize = 1)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    private AlarmTypeCode alarmTypeCode;
    private Double minValue;
    private Double maxValue;
    private String name;

    private Timestamp validFrom;
    private Timestamp validTo;
    private Timestamp createdAt;
    private String createdBy;
    private Timestamp updatedAt;
    private String updatedBy;
}
