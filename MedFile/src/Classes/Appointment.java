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
import java.util.ArrayList;
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
    private ArrayList<Appointment> listOfAppts;

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

    public Appointment searchAppt() throws SQLException {
        DBconnect dbo = new DBconnect();
        dbo.connect();
        PreparedStatement query = dbo.getCon().prepareStatement("SELECT * FROM " + dbo.getDbName() + "." + table
            + " WHERE patientID = ? OR doctorID = ?");
        if(this.patient != null) {
            query.setInt(1, this.patient.getPatientID());
        }
        else {
            query.setInt(1, 0);
        }
        if(this.doctor != null) {
            query.setInt(2, this.doctor.getId());
        }
        else {
            query.setInt(2, 0);
        }
        ResultSet results = dbo.select(query);
        writeResultSetList(results);
        dbo.disconnect();
        
        for(int i = 0; i < this.listOfAppts.size(); i++) {
            this.listOfAppts.get(i).getPatient().selectPatient();
            this.listOfAppts.get(i).getDoctor().selectUser();
            this.listOfAppts.get(i).getCreator().selectUser();
        }
        return this;
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
    
    public Appointment insertAppointment() throws SQLException {
        
        DBconnect dbo = new DBconnect();
        dbo.connect();
        PreparedStatement query = dbo.getCon().prepareStatement("INSERT INTO " + dbo.getDbName() + "." + table
            + "(patientID, doctorID, appointmentTime, timecreated, creatorID) VALUES (?,?,?,?.?)");
        query.setInt(1, this.patient.getPatientID());
        query.setInt(2, this.doctor.getId());
        query.setDate(3, new java.sql.Date(this.apptTime.getTime()));
        query.setDate(4, new java.sql.Date(this.createdTime.getTime()));
        query.setInt(5, this.creator.getId());
        ResultSet results = dbo.insertUpdate(query);
        dbo.disconnect();
        return this;
    }
    
    public Appointment updateAppointment() throws SQLException {
        
        DBconnect dbo = new DBconnect();
        dbo.connect();
        PreparedStatement query = dbo.getCon().prepareStatement("UPDATE " + dbo.getDbName() + "." + table
            + "SET doctorID = ?, appointmentTime = ?, timecreated = ?, creatorID = ? " +
            "WHERE patientID = ?");
        
        query.setInt(1, this.doctor.getId());
        query.setDate(2, new java.sql.Date(this.apptTime.getTime()));
        query.setDate(3, new java.sql.Date(this.createdTime.getTime()));
        query.setInt(4, this.creator.getId());
        query.setInt(5, this.patient.getPatientID());
        ResultSet results = dbo.insertUpdate(query);
        dbo.disconnect();
        return this;
    }
    public Appointment deleteAppointment() throws SQLException {
        
        DBconnect dbo = new DBconnect();
        dbo.connect();
        PreparedStatement query = dbo.getCon().prepareStatement("DELETE FROM " + dbo.getDbName() + "." + table +
            " WHERE patientID = ?");

        query.setInt(1, this.patient.getPatientID());
        dbo.delete(query);
        dbo.disconnect();
        return this;
    }
    private void writeResultSet(ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            this.patient = new Patient(resultSet.getInt("ID"));
            this.doctor = new User(resultSet.getInt("doctorID"));
            this.apptTime = resultSet.getDate("appointmentTime");
            this.createdTime = resultSet.getDate("timeCreated");
            this.creator = new User(resultSet.getInt("creatorID"));
        }
    }
    private void writeResultSetList(ResultSet resultSet) throws SQLException {
        this.listOfAppts = new ArrayList<Appointment>();
        while (resultSet.next()) {
            Appointment tmp = new Appointment();
            tmp.setPatient(new Patient(resultSet.getInt("ID")));
            tmp.setDoctor(new User(resultSet.getInt("doctorID")));
            tmp.setApptTime(resultSet.getDate("appointmentTime"));
            tmp.setCreatedTime(resultSet.getDate("timeCreated"));
            tmp.setCreator(new Patient(resultSet.getInt("creatorID")));
            this.listOfAppts.add(tmp);
        }
    }

    /**
     * @return the listOfAppts
     */
    public ArrayList<Appointment> getListOfAppts() {
        return listOfAppts;
    }

    /**
     * @param listOfAppts the listOfAppts to set
     */
    public void setListOfAppts(ArrayList<Appointment> listOfAppts) {
        this.listOfAppts = listOfAppts;
    }
}
