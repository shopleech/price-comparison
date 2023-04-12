package com.shopleech.publicapi.bll.service;

import com.shopleech.publicapi.bll.service.model.IProductService;
import com.shopleech.publicapi.bll.util.JwtTokenUtil;
import com.shopleech.publicapi.dal.repository.OfferRepository;
import com.shopleech.publicapi.dal.repository.ProductRepository;
import com.shopleech.publicapi.domain.Offer;
import com.shopleech.publicapi.domain.Product;
import com.shopleech.publicapi.dto.v1.ProductImportItemDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

/**
 * @author Ahto Jalak
 * @since 04.02.2023
 */
@Service
public class ProductService implements IProductService {
    Logger logger = LoggerFactory.getLogger(ProductService.class);

    @Autowired
    protected ProductRepository productRepository;
    @Autowired
    protected OfferRepository offerRepository;
    @Autowired
    protected UserService userService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    public Product get(Integer id) throws Exception {
        logger.info("product" + id);
        var item = productRepository.findById(id);

        if (item.isEmpty()) {
            throw new Exception("product not found");
        }

        return item.get();
    }

    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> getAllByKeyword(String keyword) {
        return productRepository.getAllProductsByKeyword(keyword);
    }

    @Override
    public String importProducts(String token, List<ProductImportItemDTO> productImportItems) {

        try {
            logger.info(token);
            var user = userService.getUserByUsername(jwtTokenUtil.getUsernameFromToken(token));

            var customerAccounts = user.getCustomer().getCustomerAccounts();
            var customerAccount = customerAccounts.stream().findFirst();
            if (customerAccount.isEmpty()) {
                throw new Exception("no accounts");
            }

            for (ProductImportItemDTO productImportItem : productImportItems) {

                var data = new Offer();
                data.setBarcode(productImportItem.getProductNo());
                data.setName(productImportItem.getName());
                data.setAccount(customerAccount.get().getAccount());
                data.setValidFrom(Timestamp.from(Instant.now()));
                data.setCreatedAt(Timestamp.from(Instant.now()));
                data.setUpdatedAt(Timestamp.from(Instant.now()));
                var offerAdded = offerRepository.save(data);
                logger.info(offerAdded.toString());
            }

            return "success";

        } catch (Exception e) {
            logger.info("error with import: " + e.getMessage());
        }

        return null;
    }

    @Override
    public Product add(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product update(Integer id, Product product) {
        return product;
    }

    @Override
    public Integer remove(Integer id) {
        return id;
    }

    @Override
    public List<Product> getAllByProduct(Product product) {
        return productRepository.getAllProductsByKeyword(product.getName());
    }

    public static UserDetails currentUserDetails() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        if (authentication != null) {
            Object principal = authentication.getPrincipal();
            return principal instanceof UserDetails ? (UserDetails) principal : null;
        }
        return null;
    }
}
