package com.shopleech.publicapi.bll.dto;

import com.shopleech.base.config.type.StatusTypeCode;
import lombok.*;

import java.util.List;

/**
 * @author Ahto Jalak
 * @since 23.03.2023
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CustomerBLLDTO {
    private Integer id;

    private String personalCode;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private StatusTypeCode status;

//    private UserBLLDTO user;
//
//    private List<CustomerAccountBLLDTO> customerAccounts;
//
//    private List<WatchlistBLLDTO> watchlists;
//
//    private List<AlarmBLLDTO> alarms;
//
//    private List<ReviewBLLDTO> reviews;
}
