package com.shopleech.publicapi.domain;

import com.shopleech.base.config.AlarmTypeCode;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

/**
 * @author Ahto Jalak
 * @since 05.02.2023
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "_alarm")
public class Alarm {

   @Id
   @GeneratedValue
   private Integer id;

   @ManyToOne
   private Customer customer;

   @ManyToOne
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
