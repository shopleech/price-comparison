package com.shopleech.publicapi.dto.v1;

/**
 * @author Ahto Jalak
 * @since 24.01.2023
 */
public class Account {

   int id;
   String name;
   String description;

   public Account(int id, String name, String description) {
      this.id = id;
      this.name = name;
      this.description = description;
   }

   public void setId(int id) {
      this.id = id;
   }

   public int getId() {
      return id;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getName() {
      return name;
   }

   public void setDescription(String description) {
      this.description = description;
   }

   public String getDescription() {
      return description;
   }
}
