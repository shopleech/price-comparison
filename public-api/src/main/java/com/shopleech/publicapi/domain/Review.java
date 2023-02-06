package com.shopleech.publicapi.domain;

import com.shopleech.base.config.ReviewTypeCode;
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
@Table(name = "_review")
public class Review {

   @Id
   @GeneratedValue
   private Integer id;

   @ManyToOne
   private Customer customer;

   @ManyToOne
   private AccountProduct accountProduct;

   private ReviewTypeCode reviewTypeCode;
   private Integer score;
   private String description;

   private Timestamp validFrom;
   private Timestamp validTo;
   private Timestamp createdAt;
   private String createdBy;
   private Timestamp updatedAt;
   private String updatedBy;
}
