package com.shopleech.publicapi.dal.repository;

import com.shopleech.publicapi.dal.dto.AccountDALDTO;
import com.shopleech.publicapi.dal.mapper.AccountDALMapper;
import com.shopleech.publicapi.domain.Account;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Ahto Jalak
 * @since 06.02.2023
 */
@Component
public class AccountRepositoryImpl implements AccountRepositoryCustom {
}
