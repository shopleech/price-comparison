package com.shopleech.publicapi.dal.repository;

import com.shopleech.publicapi.dal.dto.UserDALDTO;
import com.shopleech.publicapi.dal.mapper.UserDALMapper;
import com.shopleech.publicapi.domain.User;
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
public class UserRepositoryImpl implements UserRepositoryCustom {

    @Autowired
    private UserDALMapper userDALMapper;

    @PersistenceContext
    EntityManager em;

    @Override
    public void addUser(UserDALDTO userDALDTO) {

        Session session = em.unwrap(Session.class);

        User user = userDALMapper.mapToEntity(userDALDTO);
        session.persist(user);
    }

    @Override
    public UserDALDTO getUserById(Integer id) {

        User user = em.find(User.class, id);

        return userDALMapper.mapToDto(user);
    }

    @Override
    public List<UserDALDTO> getAllUsers() {

        TypedQuery<User> query = em.createQuery("select u from User u", User.class);
        List<User> users = query.getResultList();

        return userDALMapper.mapToDto(users);
    }

    @Override
    public UserDALDTO getUserByUsername(String username) {

        TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.email = :a", User.class);
        query.setParameter("a", username);
        User user = query.getSingleResult();

        return userDALMapper.mapToDto(user);
    }
}
