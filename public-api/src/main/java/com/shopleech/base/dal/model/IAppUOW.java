package com.shopleech.base.dal.model;

/**
 * @author Ahto Jalak
 * @since 24.01.2023
 */
public interface IAppUOW<T> {

   String INSERT = "INSERT";
   String MODIFY = "MODIFY";
   String DELETE = "DELETE";

   void registerNew(T entity);

   void registerModified(T entity);

   void registerDeleted(T entity);

   void commit();

   void rollback();
}
