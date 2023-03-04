package com.shopleech.publicapi.dal.dto;

import com.shopleech.base.config.AlarmTypeCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Ahto Jalak
 * @since 07.02.2023
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlarmDALDTO {

    private Integer id;
    private AlarmTypeCode alarmTypeCode;
    private Double minValue;
    private Double maxValue;
    private String name;
}
