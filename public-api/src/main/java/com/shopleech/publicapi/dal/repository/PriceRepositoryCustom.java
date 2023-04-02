package com.shopleech.publicapi.dal.repository;

import com.shopleech.publicapi.dal.dto.PriceDALDTO;

/**
 * @author Ahto Jalak
 * @since 07.02.2023
 */
public interface PriceRepositoryCustom {
    void addPrice(PriceDALDTO price);

//    PriceDALDTO getPriceById(Integer id);
//
//    List<PriceDALDTO> getAllPrices();

    int getPriceUpdateCount();
}
