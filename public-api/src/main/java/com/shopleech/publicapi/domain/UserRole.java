package com.shopleech.publicapi.domain;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

/**
 * @author Ahto Jalak
 * @since 22.03.2023
 */
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "user_role")
public class UserRole {

    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    private Timestamp validFrom;
    private Timestamp validTo;
    private Timestamp createdAt;
    private String createdBy;
    private Timestamp updatedAt;
    private String updatedBy;
}
