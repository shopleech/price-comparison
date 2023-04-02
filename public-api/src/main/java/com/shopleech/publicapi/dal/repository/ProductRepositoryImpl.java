package com.shopleech.publicapi.dal.repository;

import com.shopleech.publicapi.dal.dto.ProductDALDTO;
import com.shopleech.publicapi.dal.dto.ShopDALDTO;
import com.shopleech.publicapi.dal.mapper.ProductDALMapper;
import com.shopleech.publicapi.dal.mapper.ShopDALMapper;
import com.shopleech.publicapi.domain.Product;
import com.shopleech.publicapi.domain.Shop;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Ahto Jalak
 * @since 06.02.2023
 */
@Repository
public class ProductRepositoryImpl implements ProductRepositoryCustom {

    Logger logger = LoggerFactory.getLogger(ProductRepositoryImpl.class);

    @Autowired
    private ProductDALMapper productDALMapper;

    @Autowired
    private ShopDALMapper shopDALMapper;

    @PersistenceContext
    EntityManager em;

    @Transactional
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
        String statement = "SELECT p.* FROM product p WHERE p.name ilike :name OR p.barcode ilike :barcode";
        Query query = em.createNativeQuery(statement, Product.class);
        query.setParameter("name", keyword);
        query.setParameter("barcode", keyword);
        List<Product> products = query.getResultList();
        logger.info("products found: " + products.size());
        return productDALMapper.mapToDto(products);
    }

    @Override
    public int getProductCount() {
        String statement = "SELECT COUNT(p.*) FROM product p";
        Query query = em.createNativeQuery(statement, Integer.class);

        return (int) query.getSingleResult();
    }

    @Override
    public void addShop(String shopName) {
        String query = "insert into shop values (nextval('shop_seq'), ?)";

        em.createNativeQuery(query)
                .setParameter(1, shopName)
                .executeUpdate();
    }

    @Override
    public ShopDALDTO getShopByName(String shopName) {
        String statement = "SELECT s.* FROM shop s WHERE s.name = :name";
        Query query = em.createNativeQuery(statement, Shop.class);
        query.setParameter("name", shopName);
        Shop shop = (Shop) query.getSingleResult();

        return shopDALMapper.mapToDto(shop);
    }
}
