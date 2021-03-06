/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.util.ArrayList;

public class Doctor extends User {

    private int max = 3;
    private int current = 0;
    private int doctorID;
    private ArrayList<Patient> listOfPatients = new ArrayList<Patient>();
    private String table = "doctor";

    public Doctor() {

    }

    public Doctor(int id) {
        this.doctorID = id;
    }

    public Doctor(User user) {
        this.setId(user.getId());
        this.setFName(user.getFName());
        this.setLName(user.getLName());
        this.setPhone(user.getPhone());
        this.setSSN(user.getSSN());
        this.setSecurityID(user.getSecurityID());
        this.setEmail(user.getEmail());
        this.setPassword(user.getPassword());
        this.setAddress(user.getAddress());
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

    public String getDBTable() {
        return this.table;
    }

    /**
     * @return the max
     */
    public int getMax() {
        return max;
    }

    /**
     * @param max the max to set
     */
    public void setMax(int max) {
        this.max = max;
    }

    /**
     * @return the current
     */
    public int getCurrent() {
        return current;
    }

    /**
     * @param current the current to set
     */
    public void setCurrent(int current) {
        this.current = current;
    }

    /**
     * @return the doctorID
     */
    public int getDoctorID() {
        return doctorID;
    }

    /**
     * @param doctorID the doctorID to set
     */
    public void setDoctorID(int doctorID) {
        this.doctorID = doctorID;
    }

    /**
     * @return the listOfPatients
     */
    public ArrayList<Patient> getListOfPatients() {
        return listOfPatients;
    }

    /**
     * @param listOfPatients the listOfPatients to set
     */
    public void setListOfPatients(ArrayList<Patient> listOfPatients) {
        this.listOfPatients = listOfPatients;
    }

    public void updateUserDoctor(User user) {
        this.setId(user.getId());
        this.setFName(user.getFName());
        this.setLName(user.getLName());
        this.setPhone(user.getPhone());
        this.setSSN(user.getSSN());
        this.setSecurityID(user.getSecurityID());
        this.setEmail(user.getEmail());
        this.setPassword(user.getPassword());
        this.setAddress(user.getAddress());
    }
}
