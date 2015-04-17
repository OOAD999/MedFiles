/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import jdk.nashorn.internal.runtime.Version;

public class DBconnect {

    private Connection con;
    private Statement state;
    private PreparedStatement prepState;
    private ResultSet resultSet;
    private ResultSetMapper map = new ResultSetMapper();
    private int result;
    private String dbName;
    private String dbUser;
    private String dbPassword;
    private Logger log;

    public DBconnect() {
        this.dbName = "medfiles";
        this.dbUser = "root";
        this.dbPassword = "a";
    }

    public DBconnect(String dbName, String dbUser, String dbPassword) {
        this.dbName = dbName;
        this.dbUser = dbUser;
        this.dbPassword = dbPassword;
    }

    /**
     * @return the dbName
     */
    public String getDbName() {
        return dbName;
    }

    /**
     * @return the conn
     */
    public Connection getCon() {
        return con;
    }

    public boolean connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + getDbName(), dbUser, dbPassword);
            return true;
        } catch (Exception e) {
            //    e.printStackTrace();
            JOptionPane.showMessageDialog(null, "" + e.getMessage(), "Connection Error", JOptionPane.WARNING_MESSAGE);
            return false;
        }
    }

    public void disconnect() {
        try {
            resultSet.close();
        } catch (Exception e) { /* ignored */ }
        try {
            prepState.close();
        } catch (Exception e) { /* ignored */ }
        try {
            state.close();
        } catch (Exception e) { /* ignored */ }
        try {
            con.close();
        } catch (Exception e) { /* ignored */ }
    }

    public ResultSet select(PreparedStatement query) {
        if (this.connect()) {
            try {
                resultSet = query.executeQuery();
            } catch (SQLException ex) {
                Logger lgr = Logger.getLogger(Version.class.getName());
                lgr.log(Level.SEVERE, ex.getMessage(), ex);

                JOptionPane.showMessageDialog(null, "" + ex.getMessage(), "Communication Error", JOptionPane.WARNING_MESSAGE);
            }
        }
        return resultSet;
    }

    public ResultSet insertUpdate(PreparedStatement query) {
        if (this.connect()) {
            try {
                query.executeUpdate();
                resultSet = query.getGeneratedKeys();
            } catch (SQLException ex) {
                Logger lgr = Logger.getLogger(Version.class.getName());
                lgr.log(Level.SEVERE, ex.getMessage(), ex);
                JOptionPane.showMessageDialog(null, "" + ex.getMessage(), "Communication Error", JOptionPane.WARNING_MESSAGE);
            }
        }
        return resultSet;
    }

    public boolean delete(PreparedStatement query) {
        boolean result = false;
        if (this.connect()) {
            try {
                query.executeUpdate();
                result = true;
            } catch (SQLException ex) {
                Logger lgr = Logger.getLogger(Version.class.getName());
                lgr.log(Level.SEVERE, ex.getMessage(), ex);
                JOptionPane.showMessageDialog(null, "" + ex.getMessage(), "Communication Error", JOptionPane.WARNING_MESSAGE);
            }
        }
        return result;
    }

    public User selectUser(User user) throws SQLException {
        this.connect();
        PreparedStatement query = con.prepareStatement("SELECT * FROM " + dbName + "." + user.getDBTable()
                + " WHERE ID = ?");
        query.setInt(1, user.getId());
        ResultSet results = this.select(query);
        user = map.writeResultSet(results, user);
        this.disconnect();
        this.selectSecurity(user.getSecurityID());
        return user;
    }

    public User insertUser(User user) throws SQLException {
        this.connect();
        PreparedStatement query = con.prepareStatement("INSERT INTO " + dbName + "." + user.getDBTable()
                + " (fname, lname, pnumber, address, ssn, securityID, emailID, userPassword) VALUES (?,?,?,?,?,?,?,?)", com.mysql.jdbc.Statement.RETURN_GENERATED_KEYS);
        query.setString(1, user.getFName());
        query.setString(2, user.getLName());
        query.setString(3, user.getPhone());
        query.setString(4, user.getAddress());
        query.setString(5, user.getSSN());
        query.setInt(6, user.getSecurityID().getId());
        query.setString(7, user.getEmail());
        query.setString(8, user.getPassword());

        ResultSet results = this.insertUpdate(query);
        if (results.next()) {
            user.setId(results.getInt(1));
        }
        this.disconnect();
        return user;
    }

    public User updateUser(User user) throws SQLException {
        this.connect();
        PreparedStatement query = con.prepareStatement("UPDATE" + dbName + "." + user.getDBTable()
                + "SET fname = ?, lname = ?, pnumber = ?, address = ?, ssn = ?, securityID = ?, "
                + "emailID = ?, userPassword = ? WHERE ID = ?", com.mysql.jdbc.Statement.RETURN_GENERATED_KEYS);
        query.setString(1, user.getFName());
        query.setString(2, user.getLName());
        query.setString(3, user.getPhone());
        query.setString(4, user.getAddress());
        query.setString(5, user.getSSN());
        query.setInt(6, user.getSecurityID().getId());
        query.setString(7, user.getEmail());
        query.setString(8, user.getPassword());
        query.setInt(9, user.getId());

        ResultSet results = this.insertUpdate(query);
        this.disconnect();
        return user;
    }

    public User deleteUser(User user) throws SQLException {
        this.connect();
        PreparedStatement query = con.prepareStatement("DELETE FROM " + dbName + "." + user.getDBTable()
                + " WHERE ID = ?");

        query.setInt(1, user.getId());
        this.delete(query);
        this.disconnect();
        return user;
    }

    public SecurityProfile selectSecurity(SecurityProfile profile) throws SQLException {
        this.connect();
        PreparedStatement query = con.prepareStatement("SELECT * FROM " + dbName + "." + profile.getDBTable()
                + " WHERE ID = ?");
        query.setInt(1, profile.getId());
        ResultSet results = this.select(query);
        profile = map.writeResultSet(results, profile);
        this.disconnect();
        return profile;
    }

    public SecurityProfile insertProfile(SecurityProfile profile) throws SQLException {
        this.connect();
        PreparedStatement query = con.prepareStatement("INSERT INTO " + dbName + "." + profile.getDBTable()
                + "(appointmentSecurity, recordSecurity, userManagmentSecurity) VALUES (?,?,?)", com.mysql.jdbc.Statement.RETURN_GENERATED_KEYS);
        query.setInt(1, profile.getAppointmentLvl());
        query.setInt(2, profile.getRecordLvl());
        query.setInt(3, profile.getUserManLvl());
        ResultSet results = this.insertUpdate(query);
        if (results.next()) {
            profile.setId(results.getInt(1));
        }
        this.disconnect();
        return profile;
    }

    public ArrayList<Record> selectRecords(Patient patient) throws SQLException {
        this.connect();
        PreparedStatement query = con.prepareStatement("SELECT * FROM " + dbName + "." + new Record().getDBName()
                + " WHERE pateintID = ?");
        query.setInt(1, patient.getPatientID());
        ResultSet results = this.select(query);
        patient.setRecords(map.writeResultSet(results, new ArrayList<Record>()));
        this.disconnect();
        if (patient.getRecords() != null) {
            for (int i = 0; i < patient.getRecords().size(); i++) {
                this.selectUser(patient.getRecords().get(i).getDoctor());
            }
        }

        return patient.getRecords();
    }

    public Record insertRecord(Record record) throws SQLException {
        this.connect();
        PreparedStatement query = con.prepareStatement("INSERT INTO " + dbName + "." + record.getDBName()
                + "(pateintID, recordDate, doctorID, location, height, weight, bloodPressure, "
                + "cholesterol, reasonforVisit, doctorDiagnosis, doctorNote, labNote) "
                + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?)", com.mysql.jdbc.Statement.RETURN_GENERATED_KEYS);
        query.setInt(1, record.getPatient().getPatientID());
        query.setDate(2, new java.sql.Date(record.getServiceDate().getTime()));
        query.setInt(3, record.getDoctor().getId());
        query.setString(4, record.getDoctor().getAddress());
        query.setString(5, record.getHeight());
        query.setString(6, record.getWeight());
        query.setString(7, record.getBloodPress());
        query.setString(8, record.getCholest());
        query.setString(9, record.getReason());
        query.setString(10, record.getDiagnoses());
        query.setString(11, record.getDocNotes());
        query.setString(12, record.getLabNotes());

        ResultSet results = this.insertUpdate(query);
        if (results.next()) {
            record.setId(results.getInt(1));
        }
        this.disconnect();
        return record;
    }

    public Record updateRecord(Record record) throws SQLException {
        this.connect();
        PreparedStatement query = con.prepareStatement("UPDATE " + dbName + "." + record.getDBName()
                + " SET height = ?, weight = ?, bloodPressure = ?, "
                + "cholesterol = ?, reasonforVisit = ?, doctorDiagnosis = ?, doctorNote = ?, labNote = ? "
                + "WHERE pateintID = ? AND ID = ?", com.mysql.jdbc.Statement.RETURN_GENERATED_KEYS);

        query.setString(1, record.getHeight());
        query.setString(2, record.getWeight());
        query.setString(3, record.getBloodPress());
        query.setString(4, record.getCholest());
        query.setString(5, record.getReason());
        query.setString(6, record.getDiagnoses());
        query.setString(7, record.getDocNotes());
        query.setString(8, record.getLabNotes());
        query.setInt(9, record.getPatient().getPatientID());
        query.setInt(10, record.getId());

        ResultSet results = this.insertUpdate(query);
        if (results.next()) {
            record.setId(results.getInt(1));
        }
        this.disconnect();
        return record;
    }

    public Record deleteRecord(Record record) throws SQLException {
        this.connect();
        PreparedStatement query = con.prepareStatement("DELETE FROM " + dbName + "." + record.getDBName()
                + " WHERE pateintID = ? AND ID = ?");

        query.setInt(1, record.getPatient().getPatientID());
        query.setInt(2, record.getId());
        this.delete(query);
        this.disconnect();
        return record;
    }

    public Patient selectPatient(Patient patient) throws SQLException {
        this.connect();
        PreparedStatement query = con.prepareStatement("SELECT * FROM " + dbName + "." + patient.getDBTable()
                + " WHERE userID = ? OR patientID = ?");
        query.setInt(1, patient.getId());
        query.setInt(2, patient.getPatientID());
        ResultSet results = this.select(query);
        patient = map.writeResultSet(results, patient);
        this.disconnect();
        return patient;
    }

    public Patient insertPatient(Patient patient) throws SQLException {
        this.connect();
        PreparedStatement query = con.prepareStatement("INSERT INTO " + dbName + "." + patient.getDBTable()
                + "(userID, dob, insuranceProvider, insuranceMemberID) VALUES (?,?,?,?)", com.mysql.jdbc.Statement.RETURN_GENERATED_KEYS);
        query.setInt(1, patient.getId());
        query.setDate(2, new java.sql.Date(patient.getDob().getTime()));
        query.setString(3, patient.getInsuranceProvider());
        query.setString(4, patient.getInsuranceID());
        ResultSet results = this.insertUpdate(query);
        if (results.next()) {
            patient.setPatientID(results.getInt(1));
        }
        this.disconnect();
        return patient;
    }

    public Patient updatePatient(Patient patient) throws SQLException {
        this.connect();
        PreparedStatement query = con.prepareStatement("UPDATE " + dbName + "." + patient.getDBTable()
                + "SET dob = ?, insuranceProvider = ?, insuranceMemberID = ? WHERE patientID = ?", com.mysql.jdbc.Statement.RETURN_GENERATED_KEYS);

        query.setDate(1, new java.sql.Date(patient.getDob().getTime()));
        query.setString(2, patient.getInsuranceProvider());
        query.setString(3, patient.getInsuranceID());
        query.setInt(4, patient.getPatientID());
        ResultSet results = this.insertUpdate(query);
        this.disconnect();
        return patient;
    }

    public Patient deletePatient(Patient patient) throws SQLException {
        this.connect();
        PreparedStatement query = con.prepareStatement("DELETE FROM " + dbName + "." + patient.getDBTable()
                + " WHERE patientID = ?");

        query.setInt(1, patient.getPatientID());
        this.delete(query);
        this.disconnect();
        return patient;
    }

    public Appointment selectAppt(Appointment appt) throws SQLException {
        this.connect();
        PreparedStatement query = con.prepareStatement("SELECT * FROM " + dbName + "." + appt.getDBName()
                + " WHERE patientID = ?");
        query.setInt(1, appt.getPatient().getPatientID());
        ResultSet results = this.select(query);
        appt = map.writeResultSet(results, appt);
        this.disconnect();

        if (appt.getApptTime() != null) {
            User tmp = new User(appt.getDoctor().getId());
            Doctor tmpD;
            this.selectUser(tmp);
            tmpD = new Doctor(tmp);
            appt.setDoctor(this.selectDoctor(tmpD));
            appt.setPatient(this.selectPatient(appt.getPatient()));
            appt.setCreator(this.selectUser(appt.getCreator()));
        }
        return appt;
    }

    public Appointment insertAppointment(Appointment appt) throws SQLException {
        this.connect();
        PreparedStatement query = con.prepareStatement("INSERT INTO " + dbName + "." + appt.getDBName()
                + "(patientID, doctorID, appointmentTime, timecreated, creatorID) VALUES (?,?,?,?,?)", com.mysql.jdbc.Statement.RETURN_GENERATED_KEYS);
        query.setInt(1, appt.getPatient().getPatientID());
        query.setInt(2, appt.getDoctor().getId());
        query.setDate(3, new java.sql.Date(appt.getApptTime().getTime()));
        query.setDate(4, new java.sql.Date(appt.getCreatedTime().getTime()));
        query.setInt(5, appt.getCreator().getId());
        ResultSet results = this.insertUpdate(query);

        this.disconnect();
        return appt;
    }

    public Appointment updateAppointment(Appointment appt) throws SQLException {
        this.connect();
        PreparedStatement query = con.prepareStatement("UPDATE " + dbName + "." + appt.getDBName()
                + "SET doctorID = ?, appointmentTime = ?, timecreated = ?, creatorID = ? "
                + "WHERE patientID = ?", com.mysql.jdbc.Statement.RETURN_GENERATED_KEYS);

        query.setInt(1, appt.getDoctor().getId());
        query.setDate(2, new java.sql.Date(appt.getApptTime().getTime()));
        query.setDate(3, new java.sql.Date(appt.getCreatedTime().getTime()));
        query.setInt(4, appt.getCreator().getId());
        query.setInt(5, appt.getPatient().getPatientID());
        ResultSet results = this.insertUpdate(query);
        this.disconnect();
        return appt;
    }

    public Appointment deleteAppointment(Appointment appt) throws SQLException {
        this.connect();
        PreparedStatement query = con.prepareStatement("DELETE FROM " + dbName + "." + appt.getDBName()
                + " WHERE patientID = ?");

        query.setInt(1, appt.getPatient().getPatientID());
        this.delete(query);
        this.disconnect();
        return appt;
    }

    public Doctor selectDoctor(Doctor doc) throws SQLException {
        this.connect();
        PreparedStatement query = con.prepareStatement("SELECT * FROM " + dbName + "." + doc.getDBTable()
                + " WHERE userID = ? OR ID = ?");
        query.setInt(1, doc.getId());
        query.setInt(2, doc.getDoctorID());
        ResultSet results = this.select(query);
        doc = map.writeResultSet(results, doc);

        //get list of Patients
        query = con.prepareStatement("SELECT * FROM " + dbName + "." + "doc_patient"
                + " WHERE doctorID = ?");
        query.setInt(1, doc.getDoctorID());
        results = this.select(query);
        ArrayList<Integer> tmp = new ArrayList<Integer>();
        while (results.next()) {
            tmp.add(resultSet.getInt("patientID"));
        }
        this.disconnect();
        for (int i = 0; i < tmp.size(); i++) {
            Patient tp = new Patient(tmp.get(i));
            tp = this.selectPatient(tp);
            doc.getListOfPatients().add(tp);
        }

        return doc;
    }

    public Doctor insertDoctor(Doctor doc) throws SQLException {
        this.connect();
        PreparedStatement query = con.prepareStatement("INSERT INTO " + dbName + "." + doc.getDBTable()
                + "(userID, maxPatients, currentPatients) VALUES (?,?,?)", com.mysql.jdbc.Statement.RETURN_GENERATED_KEYS);
        query.setInt(1, doc.getId());
        query.setInt(2, doc.getMax());
        query.setInt(3, doc.getCurrent());
        ResultSet results = this.insertUpdate(query);
        if (results.next()) {
            doc.setDoctorID(results.getInt(1));
        }
        this.disconnect();
        return doc;
    }

    public Doctor addToDoctor(Patient patient, Doctor doc) throws SQLException {
        this.connect();
        PreparedStatement query = con.prepareStatement("INSERT " + dbName + "." + "doc_patient"
                + " (doctorID, patientID) VALUES (?, ?)", com.mysql.jdbc.Statement.RETURN_GENERATED_KEYS);

        query.setInt(1, doc.getDoctorID());
        query.setInt(2, patient.getPatientID());
        ResultSet results = this.insertUpdate(query);

        doc.setCurrent(doc.getCurrent() + 1);
        query = con.prepareStatement("UPDATE " + dbName + "." + doc.getDBTable()
                + " SET currentPatients = ? WHERE ID = ?", com.mysql.jdbc.Statement.RETURN_GENERATED_KEYS);

        query.setInt(1, doc.getCurrent());
        query.setInt(2, doc.getId());
        results = this.insertUpdate(query);
        if (results.next()) {
            doc.setDoctorID(results.getInt(1));
        }
        this.disconnect();
        return doc;
    }
}
