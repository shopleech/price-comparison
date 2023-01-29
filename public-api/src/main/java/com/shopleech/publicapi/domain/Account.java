package com.shopleech.publicapi.domain;

import com.shopleech.base.domain.BaseDomainEntityMetaId;
import jakarta.persistence.*;

/**
 * @author Ahto Jalak
 * @since 24.01.2023
 */
@Entity
@Table(name = "account")
public class Account extends BaseDomainEntityMetaId {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "id", nullable = false)
   private int id;

   @Column
   private String name;

   @Column
   private String description;

   public Account(int id, String name, String description) {
      this.id = id;
      this.name = name;
      this.description = description;
   }

   public Account() {

   }

   public int getId() {
      return id;
   }

   public void setId(int id) {
      this.id = id;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getDescription() {
      return description;
   }

   public void setDescription(String description) {
      this.description = description;
   }
}
