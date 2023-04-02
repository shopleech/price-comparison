package com.shopleech.publicapi.dal.dto;

import com.shopleech.base.config.type.StatusTypeCode;
import lombok.*;

/**
 * @author Ahto Jalak
 * @since 07.02.2023
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CustomerDALDTO {

    private Integer id;

    private String personalCode;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private StatusTypeCode status;

//    private UserDALDTO user;

//    private List<CustomerAccountDALDTO> customerAccounts;

//    private List<WatchlistDALDTO> watchlists;

//    private List<AlarmDALDTO> alarms;

//    private List<ReviewDALDTO> reviews;
}
