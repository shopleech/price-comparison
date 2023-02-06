package com.shopleech.publicapi.domain;

import com.shopleech.base.config.CurrencyTypeCode;
import com.shopleech.base.config.PriceTypeCode;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

/**
 * @author Ahto Jalak
 * @since 06.02.2023
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "_price")
public class Price {

   @Id
   @GeneratedValue
   private Integer id;

   @ManyToOne
   private AccountProduct accountProduct;

   private PriceTypeCode priceTypeCode;
   private Double quantity;
   private Double amount;
   private CurrencyTypeCode currency;

   private Timestamp validFrom;
   private Timestamp validTo;
   private Timestamp createdAt;
   private String createdBy;
   private Timestamp updatedAt;
   private String updatedBy;
}
