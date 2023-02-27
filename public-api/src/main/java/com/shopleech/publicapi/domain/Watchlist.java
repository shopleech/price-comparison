package com.shopleech.publicapi.domain;

import com.shopleech.base.config.WatchTypeCode;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

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

    @ManyToOne
    private Customer customer;

    @ManyToOne
    private Product product;

    private WatchTypeCode watchTypeCode;

    private Timestamp validFrom;
    private Timestamp validTo;
    private Timestamp createdAt;
    private String createdBy;
    private Timestamp updatedAt;
    private String updatedBy;

    public Integer getId() {
        return id;
    }

    public WatchTypeCode getWatchTypeCode() {
        return watchTypeCode;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setWatchTypeCode(WatchTypeCode watchTypeCode) {
        this.watchTypeCode = watchTypeCode;
    }
}
