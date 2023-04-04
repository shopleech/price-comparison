package com.shopleech.publicapi.bll.dto;

import com.shopleech.base.config.type.BarcodeTypeCode;
import com.shopleech.base.config.type.ProductTypeCode;
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
public class ProductBLLDTO {
    private Integer id;

    private CategoryBLLDTO category;

    private ProductTypeCode productTypeCode;
    private String barcode;
    private BarcodeTypeCode barcodeTypeCode;
    private String name;
    private String description;

    private List<WatchlistBLLDTO> watchlists;

    private List<AlarmBLLDTO> alarms;

    private List<ReviewBLLDTO> reviews;

    private List<OfferBLLDTO> offers;

    private List<MetricBLLDTO> metrics;
}
