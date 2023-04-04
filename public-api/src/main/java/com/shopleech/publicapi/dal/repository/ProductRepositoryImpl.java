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
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Ahto Jalak
 * @since 06.02.2023
 */
@Component
public class ProductRepositoryImpl implements ProductRepositoryCustom {
}
