package com.shopleech.publicapi.domain;

import com.shopleech.base.config.Role;
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
@Table(name = "_customer_account")
public class CustomerAccount {

   @Id
   @GeneratedValue
   private Integer id;

   @ManyToOne
   private Customer customer;

   @ManyToOne
   private Account account;

   private Role role;

   private Timestamp validFrom;
   private Timestamp validTo;
   private Timestamp createdAt;
   private String createdBy;
   private Timestamp updatedAt;
   private String updatedBy;
}
