package com.shopleech.publicapi.bll.service.model;

import com.shopleech.publicapi.domain.Product;
import com.shopleech.publicapi.dto.v1.ProductImportDTO;
import com.shopleech.publicapi.dto.v1.ProductImportItemDTO;

import java.util.List;

/**
 * @author Ahto Jalak
 * @since 04.03.2023
 */
public interface IProductService {
    Product get(Integer id) throws Exception;

    List<Product> getAll();

    List<Product> getAllByKeyword(String keyword);

    String importProducts(String token, ProductImportDTO productImportItems);

    Product add(Product product);

    Product update(Integer id, Product product);

    Integer remove(Integer id);

    List<Product> getAllByProduct(Product product);
}
