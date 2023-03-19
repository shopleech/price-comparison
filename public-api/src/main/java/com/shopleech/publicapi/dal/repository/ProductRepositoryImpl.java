package com.shopleech.publicapi.dal.repository;

import com.shopleech.publicapi.dal.dto.ProductDALDTO;
import com.shopleech.publicapi.dal.mapper.ProductDALMapper;
import com.shopleech.publicapi.domain.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    Logger logger = LoggerFactory.getLogger(ProductRepositoryImpl.class);

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

    @Override
    public List<ProductDALDTO> getAllProductsByKeyword(String keyword) {
        String statement = "SELECT p.* FROM _product p WHERE p.name ilike :name OR p.barcode ilike :barcode";
        Query query = em.createNativeQuery(statement, Product.class);
        query.setParameter("name", keyword);
        query.setParameter("barcode", keyword);
        List<Product> products = query.getResultList();
        logger.info("products found: " + products.size());
        return productDALMapper.mapToDto(products);
    }
}
