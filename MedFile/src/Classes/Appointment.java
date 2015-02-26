/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package medxfiles.classes;

import DBClasses.DBconnect;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

/**
 *
 * @author softwareProject
 */
public class Appointment {
    private Patient patient;
    private User doctor;
    private Date apptTime;
    private Date createdTime;
    private User creator;
    private String table = "appointment";

    public Appointment() {
    }

    public Appointment(Patient patient, Date apptTime) {
        this.patient = patient;
        this.apptTime = apptTime;
    }

    /**
     * @return the patient
     */
    public Patient getPatient() {
        return patient;
    }

    /**
     * @param patient the patient to set
     */
    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    /**
     * @return the doctor
     */
    public User getDoctor() {
        return doctor;
    }

    /**
     * @param doctor the doctor to set
     */
    public void setDoctor(User doctor) {
        this.doctor = doctor;
    }

    /**
     * @return the apptTime
     */
    public Date getApptTime() {
        return apptTime;
    }

    /**
     * @param apptTime the apptTime to set
     */
    public void setApptTime(Date apptTime) {
        this.apptTime = apptTime;
    }

    /**
     * @return the createdTime
     */
    public Date getCreatedTime() {
        return createdTime;
    }

    /**
     * @param createdTime the createdTime to set
     */
    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    /**
     * @return the creator
     */
    public User getCreator() {
        return creator;
    }

    /**
     * @param creator the creator to set
     */
    public void setCreator(User creator) {
        this.creator = creator;
    }

    public Appointment selectAppt(int patientID) throws SQLException {
        String where = "pateintID = " + patientID;
        DBconnect dbo = new DBconnect();
        dbo.connect();
        PreparedStatement query = dbo.getCon().prepareStatement("SELECT * FROM " + dbo.getDbName() + "." + table
            + " WHERE patientID = ?");
        query.setInt(1, patientID);
        ResultSet results = dbo.select(query);
        writeResultSet(results);
        dbo.disconnect();
        
        if(this.apptTime != null) {
            patient.selectPatient();
            doctor.selectUser();
            creator.selectUser();
        }
        return this;
    }
    
    private void writeResultSet(ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            this.patient = new Patient(resultSet.getInt("ID"));
            this.doctor = new User(resultSet.getInt("doctorID"));
            this.apptTime = resultSet.getDate("appointmentTime");
            this.createdTime = resultSet.getDate("timeCreated");
            this.creator = new Patient(resultSet.getInt("creatorID"));
        }
    }
}
