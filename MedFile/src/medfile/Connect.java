/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package medfile;

/**
 *
 * @author Ashwin
 */
import java.sql.*;  
  import java.sql.Connection;
import java.sql.DriverManager;
   public class Connect  
   {  
       static Connection conn = null;  
       public static Connection getConnect()   
       {  
           
           try  
           {  
               String userName = "root";  
               String password = "";  
               String dbname = "MedFiles";
               String url = "jdbc:mysql://localhost:3306/";  
               Class.forName ("com.mysql.jdbc.Driver");  
               conn = DriverManager.getConnection (url, userName, password);  
               System.out.println ("Database connection established");  
           }  
           catch (Exception e)  
           {  
               System.err.println ("Cannot connect to database server");  
           }  
             
           return conn;
       }  

      }  