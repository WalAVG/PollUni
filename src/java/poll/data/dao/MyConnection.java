/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poll.data.dao;

/**
 *
 * @author gigan
 */
import com.mysql.cj.jdbc.Driver;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 * Connect to Database
 * @author hany.said
 */
public class MyConnection {
/*    
     private static final String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
    private static final String DB_NAME = "mysql1";
    //il parametro noAccessToProcedureBodies nella connection string è usato per invocare le procedure senza avere permessi avanzati
    private static final String CONNECTION_STRING
            = "jdbc:mysql://localhost:3306/" + DB_NAME + "?noAccessToProcedureBodies=true&zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "admin";*/

    
    public static final String URL = "jdbc:mysql://localhost:3306/campionato";
    public static final String DB_NAME = "campionato";
    public static final String USER = "root";
    public static final String PASS = "admin";
       private static final String CONNECTION_STRING
            = "jdbc:mysql://localhost:3306/" + DB_NAME + "?noAccessToProcedureBodies=true&zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC";

    /**
     * Get a connection to database
     * @return Connection object
     */
    public static Connection getConnection()
    {
      try {
          DriverManager.registerDriver(new Driver());
          return DriverManager.getConnection(CONNECTION_STRING, USER, PASS);
      } catch (SQLException ex) {
          throw new RuntimeException("Error connecting to the database", ex);
      }
    }
    /**
     * Test Connection
     */
    public static void main(String[] args) {
        Connection connection = MyConnection.getConnection();
    }
}