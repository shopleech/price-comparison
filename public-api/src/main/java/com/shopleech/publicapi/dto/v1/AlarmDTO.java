package com.shopleech.publicapi.dto.v1;

import com.shopleech.base.config.type.AlarmTypeCode;
import com.shopleech.publicapi.domain.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Ahto Jalak
 * @since 24.01.2023
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlarmDTO {
    private Integer id;
    private Integer productId;
    private ProductDTO product;
    private AlarmTypeCode alarmTypeCode;
    private Double minValue;
    private Double maxValue;
    private String name;
}
