package com.shopleech.publicapi.dal.dto;

import com.shopleech.base.config.type.BarcodeTypeCode;
import lombok.*;

import java.util.List;

/**
 * @author Ahto Jalak
 * @since 23.03.2023
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OfferDALDTO {

    private Integer id;

//    private AccountDALDTO account;

//    private ProductDALDTO product;

    private String barcode;
    private BarcodeTypeCode barcodeTypeCode;
    private String name;
    private String description;
    private String url;

//    private List<FeatureDALDTO> features;

//    private List<PriceDALDTO> prices;

//    private List<MetricDALDTO> metrics;
}
