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
public class Appointment {
    private Patient patient;
    private User doctor;
    private Date apptTime;
    private Date createdTime;
    private User creator;

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
}
