package com.shopleech.publicapi.bll.dto;

/**
 * @author Ahto Jalak
 * @since 24.01.2023
 */
public class Account {
   int id;
   String name;
   String description;

   public Account(Integer id, String name, String description) {
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

   public void setDescription(String description) {
      this.description = description;
   }
   public String getDescription() {
      return description;
   }
}
