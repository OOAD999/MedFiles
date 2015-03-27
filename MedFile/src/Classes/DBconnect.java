/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;
    

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import jdk.nashorn.internal.runtime.Version;


/**
 *
 * @author softwareProject
 */
public class DBconnect {
    private Connection con;
    private Statement state;
    private PreparedStatement prepState;
    private ResultSet resultSet;
    private int result;
    private String dbName;
    private String dbUser;
    private String dbPassword;
    private Logger log;

    public DBconnect() {
        this.dbName = "medfiles";
        this.dbUser = "root";
        this.dbPassword = "a";
    }

    public DBconnect(String dbName, String dbUser, String dbPassword) {
        this.dbName = dbName;
        this.dbUser = dbUser;
        this.dbPassword = dbPassword;
    }
    
    /**
     * @return the dbName
     */
    public String getDbName() {
        return dbName;
    }
    
    /**
     * @return the conn
     */
    public Connection getCon() {
        return con;
    }
    
    public boolean connect() {
       try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + getDbName(), dbUser, dbPassword);
            return true;
       } catch (Exception e) {
        //    e.printStackTrace();
            JOptionPane.showMessageDialog(null,""+e.getMessage(),"Connection Error",JOptionPane.WARNING_MESSAGE);
            return false;
        } 
    }
    
    public void disconnect() {
        try { resultSet.close(); } catch (Exception e) { /* ignored */ }
        try { prepState.close(); } catch (Exception e) { /* ignored */ }
        try { state.close(); } catch (Exception e) { /* ignored */ }
        try { con.close(); } catch (Exception e) { /* ignored */ } 
    }
    
    public ResultSet select(PreparedStatement query) {
        if (this.connect()) {
            try {
                resultSet = query.executeQuery();
            } catch (SQLException ex) {
                Logger lgr = Logger.getLogger(Version.class.getName());
                lgr.log(Level.SEVERE, ex.getMessage(), ex);

                JOptionPane.showMessageDialog(null, "" + ex.getMessage(), "Communication Error", JOptionPane.WARNING_MESSAGE);
            } 
        }
        return resultSet;
    }
        
    public ResultSet insertUpdate(PreparedStatement query) {
        if (this.connect()) {
            try {
                query.executeUpdate();
                resultSet = query.getGeneratedKeys();
            } catch (SQLException ex) {
                Logger lgr = Logger.getLogger(Version.class.getName());
                lgr.log(Level.SEVERE, ex.getMessage(), ex);
                JOptionPane.showMessageDialog(null, "" + ex.getMessage(), "Communication Error", JOptionPane.WARNING_MESSAGE);
            }
        }
        return resultSet;
    }
    
    public boolean delete(PreparedStatement query) {
        boolean result = false;
        if (this.connect()) {
            try {
                query.executeUpdate();
                result = true;
            } catch (SQLException ex) {
                Logger lgr = Logger.getLogger(Version.class.getName());
                lgr.log(Level.SEVERE, ex.getMessage(), ex);
                JOptionPane.showMessageDialog(null, "" + ex.getMessage(), "Communication Error", JOptionPane.WARNING_MESSAGE);
            }
        }
        return result;
    }
}
