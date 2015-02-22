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
public class Record {
    private int id;
    private Patient patient;
    private Date serviceDate;
    private User doctor;
    private String height;
    private double weight;
    private String bloodPress;
    private int cholest;
    private String reason;
    private String diagnoses;
    private String docNotes;
    private String labNotes;

    public Record() {
    }

    public Record(int id, Patient patient) {
        this.id = id;
        this.patient = patient;
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
     * @return the serviceDate
     */
    public Date getServiceDate() {
        return serviceDate;
    }

    /**
     * @param serviceDate the serviceDate to set
     */
    public void setServiceDate(Date serviceDate) {
        this.serviceDate = serviceDate;
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
     * @return the height
     */
    public String getHeight() {
        return height;
    }

    /**
     * @param height the height to set
     */
    public void setHeight(String height) {
        this.height = height;
    }

    /**
     * @return the weight
     */
    public double getWeight() {
        return weight;
    }

    /**
     * @param weight the weight to set
     */
    public void setWeight(double weight) {
        this.weight = weight;
    }

    /**
     * @return the bloodPress
     */
    public String getBloodPress() {
        return bloodPress;
    }

    /**
     * @param bloodPress the bloodPress to set
     */
    public void setBloodPress(String bloodPress) {
        this.bloodPress = bloodPress;
    }

    /**
     * @return the cholest
     */
    public int getCholest() {
        return cholest;
    }

    /**
     * @param cholest the cholest to set
     */
    public void setCholest(int cholest) {
        this.cholest = cholest;
    }

    /**
     * @return the reason
     */
    public String getReason() {
        return reason;
    }

    /**
     * @param reason the reason to set
     */
    public void setReason(String reason) {
        this.reason = reason;
    }

    /**
     * @return the diagnoses
     */
    public String getDiagnoses() {
        return diagnoses;
    }

    /**
     * @param diagnoses the diagnoses to set
     */
    public void setDiagnoses(String diagnoses) {
        this.diagnoses = diagnoses;
    }

    /**
     * @return the docNotes
     */
    public String getDocNotes() {
        return docNotes;
    }

    /**
     * @param docNotes the docNotes to set
     */
    public void setDocNotes(String docNotes) {
        this.docNotes = docNotes;
    }

    /**
     * @return the labNotes
     */
    public String getLabNotes() {
        return labNotes;
    }

    /**
     * @param labNotes the labNotes to set
     */
    public void setLabNotes(String labNotes) {
        this.labNotes = labNotes;
    }
}
