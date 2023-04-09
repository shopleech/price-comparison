package com.shopleech.publicapi.dto.v1.mapper;

import com.shopleech.publicapi.bll.service.AccountService;
import com.shopleech.publicapi.bll.service.ProductService;
import com.shopleech.publicapi.bll.service.ShopService;
import com.shopleech.publicapi.domain.Offer;
import com.shopleech.publicapi.dto.v1.OfferDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Ahto Jalak
 * @since 05.04.2023
 */
@Component
public class OfferMapper {
    Logger logger = LoggerFactory.getLogger(OfferMapper.class);

    @Autowired
    AccountService accountService;
    @Autowired
    ProductService productService;
    @Autowired
    ShopService shopService;

    public List<OfferDTO> mapToDto(List<Offer> entities) {
        return entities.stream()
                .map(this::mapToDto).collect(Collectors.toList());
    }

    public OfferDTO mapToDto(Offer c) {
        OfferDTO dto = new OfferDTO();
        dto.setId(c.getId());
        if (c.getAccount() != null) {
            dto.setAccountId(c.getAccount().getId());
        }
        if (c.getProduct() != null) {
            dto.setProductId(c.getProduct().getId());
        }
        if (c.getShop() != null) {
            dto.setShopId(c.getShop().getId());
        }
        dto.setBarcode(c.getBarcode());
        dto.setBarcodeTypeCode(c.getBarcodeTypeCode());
        dto.setName(c.getName());
        dto.setDescription(c.getDescription());
        dto.setUrl(c.getUrl());

        return dto;
    }

    public List<Offer> mapToEntity(List<OfferDTO> entities) {
        return entities.stream()
                .map(this::mapToEntity).collect(Collectors.toList());
    }

    public Offer mapToEntity(OfferDTO entity) {
        Offer c = new Offer();
        c.setId(entity.getId());
        try {
            c.setAccount(accountService.get(entity.getAccountId()));
            c.setProduct(productService.get(entity.getProductId()));
            c.setShop(shopService.get(entity.getShopId()));
        } catch (Exception e) {
            logger.error("offer mapper failed");
        }
        c.setBarcode(entity.getBarcode());
        c.setBarcodeTypeCode(entity.getBarcodeTypeCode());
        c.setName(entity.getName());
        c.setDescription(entity.getDescription());
        c.setUrl(entity.getUrl());

        return c;
    }
}
