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
import java.util.ArrayList;
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
    private String weight;
    private String bloodPress;
    private String cholest;
    private String reason;
    private String diagnoses;
    private String docNotes;
    private String labNotes;
    private String table = "record";
    private ArrayList<Record> records;

    public Record() {
        this.records = new ArrayList<Record>();
    }

    public Record(int id, Patient patient) {
        this.records = new ArrayList<Record>();
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
    public String getWeight() {
        return weight;
    }

    /**
     * @param weight the weight to set
     */
    public void setWeight(String weight) {
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
    public String getCholest() {
        return cholest;
    }

    /**
     * @param cholest the cholest to set
     */
    public void setCholest(String cholest) {
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
    
    public ArrayList<Record> selectRecords(int patientID) throws SQLException {
        DBconnect dbo = new DBconnect();
        dbo.connect();
        PreparedStatement query = dbo.getCon().prepareStatement("SELECT * FROM " + dbo.getDbName() + "." + table
            + " WHERE pateintID = ?");
        query.setInt(1, patientID);
        ResultSet results = dbo.select(query);
        writeResultSet(results);
        dbo.disconnect();
        if(!(this.records.isEmpty())) {
            for(int i = 0; i < records.size(); i++) {
                records.get(i).getPatient().selectPatient();
                records.get(i).getDoctor().selectUser();
            }
        }
        
        return this.records;
    }
    
    private void writeResultSet(ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            Record temp = new Record();
            temp.id = resultSet.getInt("ID");
            temp.patient = new Patient(resultSet.getInt("ID"));
            temp.serviceDate = resultSet.getDate("recordDate");
            temp.doctor = new User(resultSet.getInt("doctorID"));
            temp.height = resultSet.getString("height");
            temp.weight = resultSet.getString("weight");
            temp.bloodPress = resultSet.getString("bloodPressure");
            temp.cholest = resultSet.getString("cholesterol");
            temp.reason = resultSet.getString("reasonforVisit");
            temp.diagnoses = resultSet.getString("doctorDiagnosis");
            temp.docNotes = resultSet.getString("doctorNote");
            temp.labNotes = resultSet.getString("labNote");
            
            this.records.add(temp);
        }
    }
}
