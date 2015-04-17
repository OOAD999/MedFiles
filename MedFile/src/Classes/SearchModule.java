/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class SearchModule {

    private DBconnect dbo = new DBconnect();
    private ResultSetMapper map = new ResultSetMapper();
    private ArrayList<Patient> listOfPatients;
    private ArrayList<Appointment> listOfAppts;
    private ArrayList<Doctor> listOfDocs;

    public SearchModule() {

    }

    /**
     * @return the listOfPatients
     */
    public ArrayList<Patient> getListOfPatients() {
        return listOfPatients;
    }

    public ArrayList<Appointment> getListOfAppts() {
        return listOfAppts;
    }

    public ArrayList<Doctor> getListOfDocs() {
        return listOfDocs;
    }

    /**
     * @param listOfPatients the listOfPatients to set
     */
    public void setListOfPatients(ArrayList<Patient> listOfPatients) {
        this.listOfPatients = listOfPatients;
    }

    public User searchUser(User user) throws SQLException {
        dbo.connect();
        PreparedStatement query = dbo.getCon().prepareStatement("SELECT * FROM " + dbo.getDbName() + "." + user.getDBTable()
                + " WHERE emailID = ?");
        query.setString(1, user.getEmail());
        ResultSet results = dbo.select(query);
        user = map.writeResultSet(results, user);
        dbo.disconnect();
        dbo.selectSecurity(user.getSecurityID());
        return user;
    }

    public ArrayList<Patient> searchPatients(Patient patient) throws SQLException {
        dbo.connect();
        PreparedStatement query = dbo.getCon().prepareStatement("SELECT * FROM " + dbo.getDbName() + "." + new Patient().getDBTable()
                + " WHERE patientID = ? OR dob = ?"
                + " OR insuranceProvider like ? OR insuranceMemberID like ?");

        query.setInt(1, patient.getPatientID());
        if (patient.getDob() != null) {
            query.setDate(2, new java.sql.Date(patient.getDob().getTime()));
        } else {
            query.setDate(2, new java.sql.Date(new Date().getTime()));
        }
        query.setString(3, "%" + patient.getInsuranceProvider());
        query.setString(4, "%" + patient.getInsuranceID());
        ResultSet results = dbo.select(query);
        this.listOfPatients = map.writeResultPatientsList(results);
        dbo.disconnect();

        for (int i = 0; i < this.listOfPatients.size(); i++) {
            User tmp = new User(listOfPatients.get(i).getId());
            dbo.selectUser(tmp);
            this.listOfPatients.get(i).updateUserPatient(tmp);
        }

        return this.listOfPatients;
    }

    public ArrayList<Appointment> searchAppt(Appointment appt) throws SQLException {
        dbo.connect();
        PreparedStatement query = dbo.getCon().prepareStatement("SELECT * FROM " + dbo.getDbName() + "." + new Appointment().getDBName()
                + " WHERE patientID = ? OR doctorID = ?");
        if (appt.getPatient() != null) {
            query.setInt(1, appt.getPatient().getPatientID());
        } else {
            query.setInt(1, 0);
        }
        if (appt.getDoctor() != null) {
            query.setInt(2, appt.getDoctor().getId());
        } else {
            query.setInt(2, 0);
        }
        ResultSet results = dbo.select(query);
        this.listOfAppts = map.writeResultApptList(results);
        dbo.disconnect();

        for (int i = 0; i < this.listOfAppts.size(); i++) {
            Appointment tmpAppt = this.listOfAppts.get(i);
            tmpAppt.setPatient(dbo.selectPatient(tmpAppt.getPatient()));
            tmpAppt.setCreator(dbo.selectUser(tmpAppt.getCreator()));

            User tmp = new User(tmpAppt.getDoctor().getId());
            tmp = dbo.selectUser(tmp);
            tmpAppt.getDoctor().updateUserDoctor(tmp);
        }
        return this.listOfAppts;
    }

    public ArrayList<Doctor> searchAllDocs() throws SQLException {
        dbo.connect();
        this.listOfDocs = new ArrayList<Doctor>();
        PreparedStatement query = dbo.getCon().prepareStatement("SELECT * FROM " + dbo.getDbName() + "." + new Doctor().getDBTable()
                + " WHERE currentPatients < maxPatients");

        ResultSet results = dbo.select(query);
        ArrayList<Integer> tmp = new ArrayList<Integer>();
        while (results.next()) {
            tmp.add(results.getInt("userID"));
        }
        dbo.disconnect();
        for (int i = 0; i < tmp.size(); i++) {
            User tu = new User(tmp.get(i));
            Doctor td = new Doctor(dbo.selectUser(tu));
            td = dbo.selectDoctor(td);
            this.listOfDocs.add(td);
        }
        return this.listOfDocs;
    }
}
