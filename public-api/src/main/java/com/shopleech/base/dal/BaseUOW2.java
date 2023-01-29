package com.shopleech.base.dal;

import com.shopleech.base.dal.model.IRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Ahto Jalak
 * @since 24.01.2023
 */
public abstract class BaseUOW2<T> implements IRepository<T> {

   private static final Logger LOGGER = LoggerFactory.getLogger(BaseUOW2.class);

   private AppUOW<T> uow;

   public void add(T entity) {
      if (uow == null) {
         uow = new AppUOW<T>(JdbcConnectionFactory.customizedSqlConnection());
         LOGGER.info("initialized unit of work : {}", uow.getClass().getCanonicalName());
      }
      uow.registerNew(entity);
   }

   public void update(T entity) {
      uow.registerModified(entity);
   }

   public void delete(T entity) {
      uow.registerDeleted(entity);
   }

   public void commit() {
      uow.commit();
   }
}