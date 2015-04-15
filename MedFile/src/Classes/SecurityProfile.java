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
    
    public String getDBTable() {
        return this.table;
    }
}
