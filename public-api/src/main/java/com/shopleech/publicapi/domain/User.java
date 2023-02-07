package com.shopleech.publicapi.domain;

import com.shopleech.base.config.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * @author Ahto Jalak
 * @since 04.02.2023
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "_user")
public class User implements UserDetails {

   @Id
   @GeneratedValue
   private Integer id;

   private String firstname;

   private String lastname;

   private String email;

   private String password;

   @Enumerated(EnumType.STRING)
   private Role role;

   private Timestamp validFrom;
   private Timestamp validTo;
   private Timestamp createdAt;
   private String createdBy;
   private Timestamp updatedAt;
   private String updatedBy;

   @OneToMany
   @JoinColumn(name="user_id", referencedColumnName="id")
   private Set<Customer> customers;

   @Override
   public Collection<? extends GrantedAuthority> getAuthorities() {
      return List.of(new SimpleGrantedAuthority(role.name()));
   }

   @Override
   public String getUsername() {
      return email;
   }

   @Override
   public String getPassword() {
      return password;
   }

   @Override
   public boolean isAccountNonExpired() {
      return true;
   }

   @Override
   public boolean isAccountNonLocked() {
      return true;
   }

   @Override
   public boolean isCredentialsNonExpired() {
      return true;
   }

   @Override
   public boolean isEnabled() {
      return true;
   }

   public Integer getId() {
      return id;
   }

   public String getFirstname() {
      return firstname;
   }

   public String getLastname() {
      return lastname;
   }

   public String getEmail() {
      return email;
   }

   public Role getRole() {
      return role;
   }

   public void setId(Integer id) {
      this.id = id;
   }

   public void setFirstname(String firstname) {
      this.firstname = firstname;
   }

   public void setLastname(String lastname) {
      this.lastname = lastname;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   public void setPassword(String password) {
      this.password = password;
   }

   public void setRole(Role role) {
      this.role = role;
   }
}
