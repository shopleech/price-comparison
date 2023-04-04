package com.shopleech.publicapi.bll.dto;

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
public class AlarmBLLDTO {
    private Integer id;

    private CustomerBLLDTO customer;

    private ProductBLLDTO product;

    private AlarmTypeCode alarmTypeCode;
    private Double minValue;
    private Double maxValue;
    private String name;
}
