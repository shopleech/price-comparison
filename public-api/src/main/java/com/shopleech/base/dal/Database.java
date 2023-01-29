package com.shopleech.base.dal;

import com.shopleech.base.dal.model.DataMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Ahto Jalak
 * @since 25.01.2023
 */
public class Database<T> {

   private static final Logger LOGGER = LoggerFactory.getLogger(Database.class);

   private Connection connection;

   private Statement createTable;
   private Statement dropTable;

   private PreparedStatement insert;
   private PreparedStatement selectAll;
   private PreparedStatement update;
   private PreparedStatement delete;

   private String tableName;

   DataMapper<T> mapper;

   public Database(Connection connection) {
      this.connection = connection;

      LOGGER.info("Creating statement!");
      try {
         createTable = connection.createStatement();
         dropTable = connection.createStatement();
         insert = connection.prepareStatement(insertSql());
         update = connection.prepareStatement(updateSql());
         delete = connection.prepareStatement(deleteSql());
         selectAll = connection.prepareStatement(selectAllSql());
      } catch (
              SQLException e) {
         e.printStackTrace();
      }
   }


   public Connection getConnection() {
      return connection;
   }


   private void createTable() {
      try {

         ResultSet rs = connection.getMetaData().getTables(null, null, null, null);
         boolean tableExists = false;
         while (rs.next()) {
            if (rs.getString("TABLE_NAME").equalsIgnoreCase(getTableName())) {
               tableExists = true;
               break;
            }
         }
         if (!tableExists)
            createTable.executeUpdate(createTableSql());
      } catch (SQLException e) {
         e.printStackTrace();
      }
   }

   private void dropTable() {
      try {
         ResultSet rs = connection.getMetaData().getTables(null, null, null, null);
         while (rs.next()) {
            if (rs.getString("TABLE_NAME").equalsIgnoreCase(getTableName())) {
               dropTable.executeQuery("DROP TABLE " + getTableName());
            }
         }
      } catch (SQLException e) {
         e.printStackTrace();
      }
   }

   private String insertSql() {
      LOGGER.info("table name is :{}", getTableName());
      //return "INSERT INTO "+getTableName()+"(column1) VALUES (value1)";
      return "INSERT INTO" + " portfolio" + " (id,name) VALUES (10,'noravesh')";

   }


   private String selectAllSql() {
      return "SELECT * FROM " + getTableName();
   }

   private String updateSql() {
      return "UPDATE" + getTableName() + "SET name='farshad'";
   }

   private String deleteSql() {
      return "DELETE FROM " + getTableName() + " WHERE id=?";
   }


   public List<T> getAll() {
      List<T> result = new ArrayList<T>();
      try {
         ResultSet rs = selectAll.executeQuery();
         while (rs.next()) {
            result.add((T) mapper.map(rs));
         }
      } catch (SQLException e) {
         e.printStackTrace();
      }
      return result;
   }

   public void persistAdd(T entity) {
      try {
         insert.executeUpdate();
      } catch (SQLException ex) {
         ex.printStackTrace();
      }
   }

   public void persistUpdate(T entity) {
      try {
         update.executeUpdate();
      } catch (SQLException ex) {
         ex.printStackTrace();
      }
   }

   public void persistDelete(T entity) {
      try {
         delete.executeUpdate();
      } catch (SQLException ex) {
         ex.printStackTrace();
      }
   }


   public String getTableName() {
      return tableName;
   }

   ;

   public String createTableSql() {
      return "CREATE TABLE " + getTableName() + "(ID   INT NOT NULL," +
              "NAME VARCHAR (20)   NOT NULL," +
              "PRIMARY KEY (ID))";
   }

   public void setTableName(String tableName) {
      this.tableName = tableName;
   }
}