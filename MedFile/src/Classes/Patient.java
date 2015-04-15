/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import Classes.DBconnect;
import com.mysql.jdbc.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author softwareProject
 */
public class Patient extends User{
    private int patientID;
    private Date dob;
    private String insuranceProvider;
    private String insuranceID;
    private String table = "patient";
    private ArrayList<Record> records;
    private DBconnect dbo = new DBconnect();

    public Patient() {
        this.records = new ArrayList<Record>();
    }

    public Patient(int ID) {
        super.setId(ID);
        this.records = new ArrayList<Record>();
    }

    /**
     * @return the patientID
     */
    public int getPatientID() {
        return patientID;
    }

    /**
     * @param patientID the patientID to set
     */
    public void setPatientID(int patientID) {
        this.patientID = patientID;
    }

    /**
     * @return the dob
     */
    public Date getDob() {
        return dob;
    }

    /**
     * @param dob the dob to set
     */
    public void setDob(Date dob) {
        this.dob = dob;
    }

    /**
     * @return the insuranceProvider
     */
    public String getInsuranceProvider() {
        return insuranceProvider;
    }

    /**
     * @param insuranceProvider the insuranceProvider to set
     */
    public void setInsuranceProvider(String insuranceProvider) {
        this.insuranceProvider = insuranceProvider;
    }

    /**
     * @return the insuranceID
     */
    public String getInsuranceID() {
        return insuranceID;
    }

    /**
     * @param insuranceID the insuranceID to set
     */
    public void setInsuranceID(String insuranceID) {
        this.insuranceID = insuranceID;
    }
    /**
     * @return the id
     */
    public int getId() {
        return super.getId();
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        super.setId(id);
    }
    /**
     * @return the email
     */
    public String getEmail() {
        return super.getEmail();
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        super.setEmail(email);
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return super.getPassword();
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        super.setPassword(password);
    }

    /**
     * @return the name
     */
    public String getFName() {
        return super.getFName();
    }

    /**
     * @param name the name to set
     */
    public void setFName(String name) {
        super.setFName(name);
    }
    
        /**
     * @return the name
     */
    public String getLName() {
        return super.getLName();
    }

    /**
     * @param name the name to set
     */
    public void setLName(String name) {
        super.setLName(name);
    }

    /**
     * @return the SSN
     */
    public String getSSN() {
        return super.getSSN();
    }
    /**
     * @return the SSN
     */
    public String getMaskSSN() {
        return super.getMaskSSN();
    }
    /**
     * @param SSN the SSN to set
     */
    public void setSSN(String SSN) {
        super.setSSN(SSN);
    }

    /**
     * @return the phone
     */
    public String getPhone() {
        return super.getPhone();
    }
    
    /**
     * @return the phone
     */
    public String getFormatPhone() {
        return super.getFormatPhone();
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        super.setPhone(phone);
    }

    /**
     * @return the securityID
     */
    public SecurityProfile getSecurityID() {
        return super.getSecurityID();
    }

    /**
     * @param securityID the securityID to set
     */
    public void setSecurityID(SecurityProfile securityID) {
        super.setSecurityID(securityID);
    }

    /**
     * @return the addr1
     */
    public String getAddr1() {
        return super.getAddr1();
    }

    /**
     * @param addr1 the addr1 to set
     */
    public void setAddr1(String addr1) {
        super.setAddr1(addr1);
    }
    
    /**
     * @return the addr2
     */
    public String getAddr2() {
        return super.getAddr2();
    }

    /**
     * @param addr2 the addr2 to set
     */
    public void setAddr2(String addr2) {
        super.setAddr2(addr2);
    }

    /**
     * @return the city
     */
    public String getCity() {
        return super.getCity();
    }

    /**
     * @param city the city to set
     */
    public void setCity(String city) {
        super.setCity(city);
    }

    /**
     * @return the state
     */
    public String getState() {
        return super.getState();
    }

    /**
     * @param state the state to set
     */
    public void setState(String state) {
        super.setState(state);
    }

    /**
     * @return the zip
     */
    public String getZip() {
        return super.getZip();
    }

    /**
     * @param zip the zip to set
     */
    public void setZip(String zip) {
        super.setZip(zip);
    }
    
    /**
     * @return the full address
     */
    public String getFullAddress() {
        return super.getFullAddress();
    }
    public String getDBTable() {
        return this.table;
    }
    /**
     * @return the listOfPatients
     */
    public ArrayList<Record> getRecords() {
        return this.records;
    }
    public void setRecords(ArrayList<Record> records) {
        this.records = records;
    }
    public ArrayList<Record> getAllRecords() throws SQLException {
        this.records = dbo.selectRecords(this);
        return this.records;
    }

}
