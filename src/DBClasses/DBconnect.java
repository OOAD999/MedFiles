/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBClasses;
    

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Version;
import javax.swing.JOptionPane;


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
        this.dbName = "medx";
        this.dbUser = "root";
        this.dbPassword = "a";
    }

    public DBconnect(String dbName, String dbUser, String dbPassword) {
        this.dbName = dbName;
        this.dbUser = dbUser;
        this.dbPassword = dbPassword;
    }
    
    public boolean connect() {
       try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + dbName, dbUser, dbPassword);
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
    
    public ResultSet select(String table) {
        if (this.connect()) {
            String query = "SELECT * FROM " + dbName + "." + table;
            try {
                state = con.createStatement();
                resultSet = state.executeQuery(query);
            } catch (SQLException ex) {
                Logger lgr = Logger.getLogger(Version.class.getName());
                lgr.log(Level.SEVERE, ex.getMessage(), ex);

                JOptionPane.showMessageDialog(null, "" + ex.getMessage(), "Communication Error", JOptionPane.WARNING_MESSAGE);
            } 
        }
        return resultSet;
    }
    
    public ResultSet select(String table, String where) {
        if (this.connect()) {
            String query = "SELECT * FROM " + dbName + "." + table
                    + "WHERE " + where;
            try {
                state = con.createStatement();
                resultSet = state.executeQuery(query);
            } catch (SQLException ex) {
                Logger lgr = Logger.getLogger(Version.class.getName());
                lgr.log(Level.SEVERE, ex.getMessage(), ex);
                JOptionPane.showMessageDialog(null, "" + ex.getMessage(), "Communication Error", JOptionPane.WARNING_MESSAGE);
            }
        }
        return resultSet;
    }
    
    public int insertUpdate(String table, String insert, String update) {
        if (this.connect()) {
            String query = "INSERT INTO " + dbName + "." + table
                    + "ON DUPLICATE KEY UPDATE " + update;
            try {
                state = con.createStatement();
                result = state.executeUpdate(query);
            } catch (SQLException ex) {
                Logger lgr = Logger.getLogger(Version.class.getName());
                lgr.log(Level.SEVERE, ex.getMessage(), ex);
                JOptionPane.showMessageDialog(null, "" + ex.getMessage(), "Communication Error", JOptionPane.WARNING_MESSAGE);
            }
        }
        return result;
    }
    
    public int delete(String table) {
        if (this.connect()) {
            String query = "DELETE FROM " + dbName + "." + table;
            try {
                state = con.createStatement();
                result = state.executeUpdate(query);
            } catch (SQLException ex) {
                Logger lgr = Logger.getLogger(Version.class.getName());
                lgr.log(Level.SEVERE, ex.getMessage(), ex);
                JOptionPane.showMessageDialog(null, "" + ex.getMessage(), "Communication Error", JOptionPane.WARNING_MESSAGE);
            }
        }
        return result;
    }
    
    public int delete(String table, String where) {
        if (this.connect()) {
            String query = "DELETE FROM " + dbName + "." + table
                    + "WHERE " + where;
            try {
                state = con.createStatement();
                result = state.executeUpdate(query);
            } catch (SQLException ex) {
                Logger lgr = Logger.getLogger(Version.class.getName());
                lgr.log(Level.SEVERE, ex.getMessage(), ex);
                JOptionPane.showMessageDialog(null, "" + ex.getMessage(), "Communication Error", JOptionPane.WARNING_MESSAGE);
            }
        }
        return result;
    }
  
}
