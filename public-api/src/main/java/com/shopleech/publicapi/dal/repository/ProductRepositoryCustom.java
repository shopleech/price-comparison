package com.shopleech.publicapi.dal.repository;

import com.shopleech.publicapi.dal.dto.ProductDALDTO;
import com.shopleech.publicapi.dal.dto.ShopDALDTO;

import java.util.List;

/**
 * @author Ahto Jalak
 * @since 06.02.2023
 */
public interface ProductRepositoryCustom {

    void addProduct(ProductDALDTO product);

    ProductDALDTO getProductById(Integer id);

    List<ProductDALDTO> getAllProducts();

    List<ProductDALDTO> getAllProductsByKeyword(String keyword);

    int getProductCount();

    void addShop(String shopName);

    ShopDALDTO getShopByName(String shopName);
}
