package com.shopleech.publicapi.bll.service;

import com.shopleech.publicapi.bll.dto.ProductBLLDTO;
import com.shopleech.publicapi.bll.mapper.ProductBLLMapper;
import com.shopleech.publicapi.dal.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Ahto Jalak
 * @since 04.02.2023
 */
@Service
@RequiredArgsConstructor
public class ProductService implements IProductService {

    @Autowired
    protected ProductRepository productRepository;

    @Autowired
    protected ProductBLLMapper productMapper;

    public void createProduct(ProductBLLDTO data) {
        productRepository.addProduct(productMapper.mapToEntity(data));
    }

    public ProductBLLDTO get(Integer id) {
        return productMapper.mapToDto(productRepository.getProductById(id));
    }

    @Override
    public List<ProductBLLDTO> getAll() {
        return productMapper.mapToDto(productRepository.getAllProducts());
    }

    @Override
    public List<ProductBLLDTO> getAllByKeyword(String keyword) {
        return productMapper.mapToDto(productRepository.getAllProductsByKeyword(keyword));
    }
}
