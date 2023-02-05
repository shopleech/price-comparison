package com.shopleech.publicapi.domain;

import com.shopleech.base.domain.BaseDomainEntityMetaId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.Set;

/**
 * @author Ahto Jalak
 * @since 24.01.2023
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "_account")
public class Account extends BaseDomainEntityMetaId {

   @Id
   @GeneratedValue
   private Integer id;

   private String name;

   private String description;

   @OneToMany
   private Set<AccountUser> accountUsers;

   public Integer getId() {
      return id;
   }

   public String getName() {
      return name;
   }

   public String getDescription() {
      return description;
   }

   public void setId(Integer id) {
      this.id = id;
   }

   public void setName(String name) {
      this.name = name;
   }

   public void setDescription(String description) {
      this.description = description;
   }
}
