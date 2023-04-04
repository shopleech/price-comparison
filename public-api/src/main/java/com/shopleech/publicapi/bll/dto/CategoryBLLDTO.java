package com.shopleech.publicapi.bll.dto;

import com.shopleech.base.config.type.CategoryTypeCode;
import lombok.*;

import java.util.List;

/**
 * @author Ahto Jalak
 * @since 07.02.2023
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CategoryBLLDTO {
    private Integer id;

    private CategoryBLLDTO parentCategory;

    private String name;
    private CategoryTypeCode categoryTypeCode;

    private List<ProductBLLDTO> products;

    private List<CategoryBLLDTO> categories;
}
