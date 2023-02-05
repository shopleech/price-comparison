package com.shopleech.publicapi.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

/**
 * @author Ahto Jalak
 * @since 05.02.2023
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "_account_user")
public class AccountUser {

   @Id
   @GeneratedValue
   private Integer id;

   @OneToOne
   private User user;

   @OneToOne
   private Account account;

   @Enumerated(EnumType.STRING)
   private Role role;
}
