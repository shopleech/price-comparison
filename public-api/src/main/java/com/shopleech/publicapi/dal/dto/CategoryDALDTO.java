package com.shopleech.publicapi.dal.dto;

import com.shopleech.base.config.type.CategoryTypeCode;
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
public class CategoryDALDTO {

    private Integer id;

//    private CategoryDALDTO parentCategory;

    private String name;
    private CategoryTypeCode categoryTypeCode;

//    private List<ProductDALDTO> products;

//    private List<CategoryDALDTO> categories;
}
