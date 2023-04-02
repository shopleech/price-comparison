package com.shopleech.publicapi.dal.dto;

import com.shopleech.base.config.type.AlarmTypeCode;
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
public class AlarmDALDTO {

    private Integer id;

//    private CustomerDALDTO customer;

//    private ProductDALDTO product;

    private AlarmTypeCode alarmTypeCode;
    private Double minValue;
    private Double maxValue;
    private String name;
}
