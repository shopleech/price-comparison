package com.shopleech.base.dal.model;

import jakarta.persistence.Entity;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Ahto Jalak
 * @since 25.01.2023
 */
public interface DataMapper<T> {
   public Entity map(ResultSet rs) throws SQLException;
}
