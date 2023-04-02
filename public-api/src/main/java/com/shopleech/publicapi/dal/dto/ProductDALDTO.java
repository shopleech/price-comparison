package com.shopleech.publicapi.dal.dto;

import com.shopleech.base.config.type.BarcodeTypeCode;
import com.shopleech.base.config.type.ProductTypeCode;
import lombok.*;

/**
 * @author Ahto Jalak
 * @since 05.02.2023
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductDALDTO {

    private Integer id;

//    private CategoryDALDTO category;

    private ProductTypeCode productTypeCode;
    private String barcode;
    private BarcodeTypeCode barcodeTypeCode;
    private String name;
    private String description;

//    private List<WatchlistDALDTO> watchlists;

//    private List<AlarmDALDTO> alarms;

//    private List<ReviewDALDTO> reviews;

//    private List<OfferDALDTO> offers;

//    private List<MetricDALDTO> metrics;
}
