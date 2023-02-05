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
@Table(name = "_watchlist")
public class Watchlist {
   @Id
   @GeneratedValue
   private Integer id;

   @OneToOne
   private User user;

   @OneToMany
   private Set<Product> products;

}
