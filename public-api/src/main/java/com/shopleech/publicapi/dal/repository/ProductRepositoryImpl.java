package com.shopleech.publicapi.dal.repository;

import com.shopleech.publicapi.dal.dto.ProductDALDTO;
import com.shopleech.publicapi.dal.mapper.ProductDALMapper;
import com.shopleech.publicapi.domain.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Ahto Jalak
 * @since 06.02.2023
 */
@Repository
@Transactional
public class ProductRepositoryImpl implements ProductRepositoryCustom {

    @Autowired
    private ProductDALMapper productDALMapper;

    @PersistenceContext
    EntityManager em;

    @Override
    public void addProduct(ProductDALDTO productDALDTO) {
        Session session = em.unwrap(Session.class);

        Product product = productDALMapper.mapToEntity(productDALDTO);
        session.persist(product);
    }

    @Override
    public ProductDALDTO getProductById(Integer id) {
        Product product = em.find(Product.class, id);
        return productDALMapper.mapToDto(product);
    }

    @Override
    public List<ProductDALDTO> getAllProducts() {
        TypedQuery<Product> query = em.createQuery("select p from Product p", Product.class);
        List<Product> product = query.getResultList();

        return productDALMapper.mapToDto(product);
    }
}
