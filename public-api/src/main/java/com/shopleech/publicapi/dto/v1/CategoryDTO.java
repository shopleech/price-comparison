package com.shopleech.publicapi.dto.v1;

import com.shopleech.base.config.type.CategoryTypeCode;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Ahto Jalak
 * @since 03.04.2023
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {
    private Integer id;
    private Integer parentCategoryId;
    private String name;
    @Enumerated(EnumType.STRING)
    private CategoryTypeCode categoryTypeCode;
}
