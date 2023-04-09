package com.shopleech.publicapi.dal.repository;

import com.shopleech.publicapi.dal.dto.RoleDALDTO;
import com.shopleech.publicapi.dal.dto.UserDALDTO;
import com.shopleech.publicapi.dal.mapper.RoleDALMapper;
import com.shopleech.publicapi.dal.mapper.UserDALMapper;
import com.shopleech.publicapi.domain.Role;
import com.shopleech.publicapi.domain.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Ahto Jalak
 * @since 06.02.2023
 */
@Component
public class UserRepositoryImpl implements UserRepositoryCustom {
    Logger logger = LoggerFactory.getLogger(UserRepositoryImpl.class);
}
