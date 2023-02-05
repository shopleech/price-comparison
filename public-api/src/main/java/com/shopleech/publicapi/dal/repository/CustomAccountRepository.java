package com.shopleech.publicapi.dal.repository;

import com.shopleech.publicapi.dal.mapper.AccountDALMapper;
import com.shopleech.publicapi.domain.Account;
import com.shopleech.publicapi.dal.model.repository.IAccountRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Ahto Jalak
 * @since 24.01.2023
 */
public class CustomAccountRepository implements IAccountRepository {

   private static final Logger LOGGER = LoggerFactory.getLogger(CustomAccountRepository.class);

   @PersistenceContext
   private EntityManager entityManager;

   protected AccountDALMapper mapper;

   @Transactional
   public void save(Account contact) {
      entityManager.persist(contact);
   }

   @Override
   public com.shopleech.publicapi.dal.dto.Account getById(int id) {
      return null;
   }

   @Override
   public com.shopleech.publicapi.dal.dto.Account getByName(String name) {
      return null;
   }

   @Override
   public List<com.shopleech.publicapi.dal.dto.Account> getAll() {
      String jpql = "SELECT c FROM Account c";
      TypedQuery<Account> query = entityManager.createQuery(jpql, Account.class);

      return mapper.mapToDto(query.getResultList());
   }

   @Override
   public void add(com.shopleech.publicapi.dal.dto.Account repo) {
      entityManager.getTransaction().begin();
      entityManager.persist(repo);
      entityManager.getTransaction().commit();
   }

   @Override
   public void update(com.shopleech.publicapi.dal.dto.Account repo) {

   }

   @Override
   public void delete(com.shopleech.publicapi.dal.dto.Account repo) {

   }

   @Override
   public List<com.shopleech.publicapi.dal.dto.Account> getBestAccounts() {

      return null;
   }
}
