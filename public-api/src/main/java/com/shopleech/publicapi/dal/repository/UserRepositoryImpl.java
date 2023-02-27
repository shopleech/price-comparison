package com.shopleech.publicapi.dal.repository;

import com.shopleech.publicapi.dal.dto.UserDALDTO;
import com.shopleech.publicapi.dal.mapper.UserDALMapper;
import com.shopleech.publicapi.domain.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Ahto Jalak
 * @since 06.02.2023
 */
@Repository
public class UserRepositoryImpl implements UserRepositoryCustom {

    @Autowired
    private UserDALMapper userDALMapper;

    @PersistenceContext
    EntityManager em;

    @Transactional
    @Override
    public void addUser(UserDALDTO user) {

        String query = "INSERT INTO _users ( firstname, lastname, email, enabled, password)"
                + " VALUES ( :a, :b, :c, :d, :e)";

        em.createNativeQuery(query)
                .setParameter("a", user.getFirstname())
                .setParameter("b", user.getLastname())
                .setParameter("c", user.getEmail())
                .setParameter("d", user.isEnabled())
                .setParameter("e", user.getPassword())
                .executeUpdate();
    }

    @Override
    public UserDALDTO getUserById(Integer id) {
        return null;
    }

    @Override
    public List<UserDALDTO> getAllUsers() {
        return null;
    }

    @Override
    public UserDALDTO getUserByUsername(String username) {

        Query query = em.createNativeQuery("SELECT em.* FROM _users as em " +
                "WHERE em.email = :a", User.class);
        query.setParameter("a", username);

        return userDALMapper.mapToDto((User) query.getSingleResult());
    }
}
