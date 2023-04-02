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

    @Autowired
    private UserDALMapper userDALMapper;

    @Autowired
    private RoleDALMapper roleDALMapper;

    @PersistenceContext
    EntityManager em;

    @Override
    public UserDALDTO addUser(UserDALDTO userDALDTO) {

        Session session = em.unwrap(Session.class);

        User user = userDALMapper.mapToEntity(userDALDTO);
        session.persist(user);

        return userDALMapper.mapToDto(user);
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

//    @Override
//    public User getUserByUsername(String username) {
//
//        String sql = "SELECT * FROM _user u WHERE u.email = :a";
//        Query query = em.createNativeQuery(sql, User.class);
//        query.setParameter("a", username);
//
//        return (User) query.getSingleResult();
//    }

//    @Override
//    public boolean isUsernameExist(String username) {
//        String statement = "SELECT COUNT(u.*) FROM _user u WHERE u.email = :a";
//        Query query = em.createNativeQuery(statement, Integer.class);
//        query.setParameter("a", username);
//        var result = (int) query.getSingleResult();
//
//        return result > 0;
//    }

    @Override
    public int getUserCount() {
        String statement = "SELECT COUNT(u.*) FROM _user u";
        Query query = em.createNativeQuery(statement, Integer.class);

        return (int) query.getSingleResult();
    }

    @Override
    public RoleDALDTO getRole(String roleName) {
        Query query = em.createNativeQuery("SELECT r FROM role r WHERE r.name = :a", Role.class);
        query.setParameter("a", roleName);
        Role role = (Role) query.getSingleResult();

        return roleDALMapper.mapToDto(role);
    }

    @Override
    public UserDALDTO updateUser(UserDALDTO mapToEntity) {

        String query = "update _user set customer_id = ? where id = ?";

        em.createNativeQuery(query)
                .setParameter(1, mapToEntity.getCustomer().getId())
                .setParameter(2, mapToEntity.getId())
                .executeUpdate();

        logger.info("customer: " + mapToEntity.getCustomer().getId());
        logger.info("update user: " + mapToEntity.getId());

//        Session session = em.unwrap(Session.class);
//
//        User user = userDALMapper.mapToEntity(mapToEntity);
//        session.persist(user);

        return mapToEntity;
    }
}
