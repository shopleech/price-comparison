package com.shopleech.publicapi.bll.service.model;

import com.shopleech.publicapi.domain.Price;

import java.util.List;

/**
 * @author Ahto Jalak
 * @since 05.04.2023
 */
public interface IPriceService {
    List<Price> getAll();

    Price get(Integer id) throws Exception;

    Price add(Price price);

    Price update(Integer id, Price price);

    Integer remove(Integer id);

    Double getMinPriceByBarcode(String barcode);

    Price getLastPriceByOfferId(Integer offerId);
}
