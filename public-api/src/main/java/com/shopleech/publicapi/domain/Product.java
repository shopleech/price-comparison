package com.shopleech.publicapi.domain;

import com.shopleech.base.config.BarcodeTypeCode;
import com.shopleech.base.config.ProductTypeCode;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Set;

/**
 * @author Ahto Jalak
 * @since 04.02.2023
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "_product")
public class Product {

   @Id
   @GeneratedValue
   private Integer id;

   @ManyToOne
   private Category category;

   private ProductTypeCode productTypeCode;
   private String barcode;
   private BarcodeTypeCode barcodeTypeCode;
   private String name;
   private String description;

   private Timestamp validFrom;
   private Timestamp validTo;
   private Timestamp createdAt;
   private String createdBy;
   private Timestamp updatedAt;
   private String updatedBy;

   @OneToMany
   @JoinColumn(name="product_id", referencedColumnName="id")
   private Set<Watchlist> watchlists;

   @OneToMany
   @JoinColumn(name="product_id", referencedColumnName="id")
   private Set<Alarm> alarms;

   @OneToMany
   @JoinColumn(name="product_id", referencedColumnName="id")
   private Set<AccountProduct> accountProducts;

   public Integer getId() {
      return id;
   }

   public ProductTypeCode getProductTypeCode() {
      return productTypeCode;
   }

   public String getBarcode() {
      return barcode;
   }

   public BarcodeTypeCode getBarcodeTypeCode() {
      return barcodeTypeCode;
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

   public void setProductTypeCode(ProductTypeCode productTypeCode) {
      this.productTypeCode = productTypeCode;
   }

   public void setBarcode(String barcode) {
      this.barcode = barcode;
   }

   public void setBarcodeTypeCode(BarcodeTypeCode barcodeTypeCode) {
      this.barcodeTypeCode = barcodeTypeCode;
   }

   public void setName(String name) {
      this.name = name;
   }

   public void setDescription(String description) {
      this.description = description;
   }
}
