/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import Classes.DBconnect;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author softwareProject
 */
public class SecurityProfile {
    private int id;
    private int appointmentLvl;
    private int recordLvl;
    private int userManLvl;
    String table = "securityprofile";

    public SecurityProfile() {
    }

    public SecurityProfile(int id) {
        this.id = id;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the appointmentLvl
     */
    public int getAppointmentLvl() {
        return appointmentLvl;
    }

    /**
     * @param appointmentLvl the appointmentLvl to set
     */
    public void setAppointmentLvl(int appointmentLvl) {
        this.appointmentLvl = appointmentLvl;
    }

    /**
     * @return the recordLvl
     */
    public int getRecordLvl() {
        return recordLvl;
    }

    /**
     * @param recordLvl the recordLvl to set
     */
    public void setRecordLvl(int recordLvl) {
        this.recordLvl = recordLvl;
    }

    /**
     * @return the userManLvl
     */
    public int getUserManLvl() {
        return userManLvl;
    }

    /**
     * @param userManLvl the userManLvl to set
     */
    public void setUserManLvl(int userManLvl) {
        this.userManLvl = userManLvl;
    }
    
    public SecurityProfile selectSecurity() throws SQLException {
        DBconnect dbo = new DBconnect();
        dbo.connect();
        PreparedStatement query = dbo.getCon().prepareStatement("SELECT * FROM " + dbo.getDbName() + "." + table
            + " WHERE ID = ?");
        query.setInt(1, this.id);
        ResultSet results = dbo.select(query);
        dbo.disconnect();
        return this;
    }
    public SecurityProfile insertProfile() throws SQLException {
        
        DBconnect dbo = new DBconnect();
        dbo.connect();
        PreparedStatement query = dbo.getCon().prepareStatement("INSERT INTO " + dbo.getDbName() + "." + table
            + "(appointmentSecurity, recordSecurity, userManagmentSecurity) VALUES (?,?,?)");
        query.setInt(1, this.appointmentLvl);
        query.setInt(2, this.recordLvl);
        query.setInt(3, this.userManLvl);
        ResultSet results = dbo.insertUpdate(query);
        if(results.next()) {
            this.id = results.getInt(1);
        }
        dbo.disconnect();
        return this;
    }
    private void writeResultSet(ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            this.id = resultSet.getInt("ID");
            this.appointmentLvl = resultSet.getInt("appointmentSecurity");
            this.recordLvl = resultSet.getInt("appointmentSecurity");
            this.userManLvl = resultSet.getInt("appointmentSecurity");
        }
    }
}
