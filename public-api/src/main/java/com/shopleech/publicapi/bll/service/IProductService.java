package com.shopleech.publicapi.bll.service;

import com.shopleech.publicapi.bll.dto.ProductBLLDTO;

import java.util.List;

/**
 * @author Ahto Jalak
 * @since 04.03.2023
 */
public interface IProductService {

    List<ProductBLLDTO> getAll();
}
