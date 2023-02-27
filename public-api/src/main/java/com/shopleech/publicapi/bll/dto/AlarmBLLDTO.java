package com.shopleech.publicapi.bll.dto;

import com.shopleech.base.config.AlarmTypeCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Ahto Jalak
 * @since 07.02.2023
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AlarmBLLDTO {

    private Integer id;
    private AlarmTypeCode alarmTypeCode;
    private Double minValue;
    private Double maxValue;
    private String name;
}
