/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package medxfiles.classes;

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

    public Patient() {
    }

    public Patient(int patientID) {
        this.patientID = patientID;
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

}
