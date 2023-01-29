package com.shopleech.publicapi.domain;

import jakarta.persistence.*;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * @author Ahto Jalak
 * @since 27.01.2023
 */
@Entity
public class AppUserEntity implements Serializable {

   @Id
   @Column(name = "id")
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Column(name = "email", nullable = false, length = 50)
   private String email;

   @Column(name = "password", nullable = false, length = 60)
   private String password;

   @Column(name = "nick", nullable = false, length = 20)
   private String nick;

   @Column(name = "is_admin", columnDefinition = "boolean default false")
   private boolean isAdmin;

   @Column(name = "create_date", nullable = false, insertable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
   private Timestamp createDate;

   @Transient
   private String confirmPassword;

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      AppUserEntity appUser = (AppUserEntity) o;
      return Objects.equals(id, appUser.id);
   }

   @Override
   public int hashCode() {
      return Objects.hash(id);
   }
}

