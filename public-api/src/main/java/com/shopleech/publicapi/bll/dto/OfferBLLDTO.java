package com.shopleech.publicapi.bll.dto;

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
public class OfferBLLDTO {

    private Integer id;

    private AccountBLLDTO account;

    private ProductBLLDTO product;

    private String barcode;
    private BarcodeTypeCode barcodeTypeCode;
    private String name;
    private String description;
    private String url;

    private List<FeatureBLLDTO> features;

    private List<PriceBLLDTO> prices;

    private List<MetricBLLDTO> metrics;
}
