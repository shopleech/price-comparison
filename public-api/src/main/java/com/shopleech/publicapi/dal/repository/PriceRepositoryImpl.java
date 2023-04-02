package com.shopleech.publicapi.dal.repository;

import com.shopleech.publicapi.dal.dto.PriceDALDTO;
import com.shopleech.publicapi.dal.mapper.PriceDALMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author Ahto Jalak
 * @since 06.02.2023
 */
public class PriceRepositoryImpl implements PriceRepositoryCustom {

    @Autowired
    private PriceDALMapper mapper;

    @PersistenceContext
    EntityManager em;

    @Override
    public void addPrice(PriceDALDTO price) {
        // priceRepository.add(mapper.mapToEntity(price));
    }

//    @Override
//    public PriceDALDTO getPriceById(Integer id) {
//        return mapper.mapToDto(priceRepository.getReferenceById(id));
//    }
//
//    @Override
//    public List<PriceDALDTO> getAllPrices() {
//        return mapper.mapToDto(priceRepository.findAll());
//    }

    @Override
    public int getPriceUpdateCount() {
        String statement = "SELECT COUNT(pr.*) FROM price pr";
        Query query = em.createNativeQuery(statement, Integer.class);

        return (int) query.getSingleResult();
    }
}
