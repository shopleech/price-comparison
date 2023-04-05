package com.shopleech.publicapi.bll.service.model;

import com.shopleech.publicapi.domain.Offer;

import java.util.List;

/**
 * @author Ahto Jalak
 * @since 05.04.2023
 */
public interface IOfferService {
    List<Offer> getAll();

    Offer get(Integer id) throws Exception;

    Offer add(Offer offer);

    Offer update(Integer id, Offer offer);

    Integer remove(Integer id);
}
