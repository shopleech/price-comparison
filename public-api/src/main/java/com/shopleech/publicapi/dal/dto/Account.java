package com.shopleech.publicapi.dal.dto;

/**
 * @author Ahto Jalak
 * @since 24.01.2023
 */
public class Account {
   private int id;

   private String name;

   private String description;

   public Account(int id, String name, String description) {
      this.id = id;
      this.name = name;
      this.description = description;
   }

   public Account() {

   }

   public Integer getId() {
      return id;
   }

   public void setId(int id) {
      this.id = id;
   }

   public String getName() {
      return name;
   }

   public String getDescription() {
      return description;
   }

   public void setName(String name) {
      this.name = name;
   }

   public void setDescription(String description) {
      this.description = description;
   }
}
