/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ResultSetMapper {

    public ResultSetMapper() {

    }

    public Appointment writeResultSet(ResultSet resultSet, Appointment appt) throws SQLException {
        while (resultSet.next()) {
            Patient tmp = new Patient();
            User tmpU = new User();
            tmp.setPatientID(resultSet.getInt("patientID"));
            appt.setPatient(tmp);
            tmpU = new User(resultSet.getInt("doctorID"));
            appt.setDoctor(new Doctor(tmpU));
            appt.setApptTime(resultSet.getDate("appointmentTime"));
            appt.setCreatedTime(resultSet.getDate("timeCreated"));
            appt.setCreator(new User(resultSet.getInt("creatorID")));
        }
        return appt;
    }

    public Patient writeResultSet(ResultSet resultSet, Patient patient) throws SQLException {
        while (resultSet.next()) {
            patient.setPatientID(resultSet.getInt("patientID"));
            patient.setId(resultSet.getInt("userID"));
            patient.setDob(resultSet.getDate("dob"));
            patient.setInsuranceProvider(resultSet.getString("insuranceProvider"));
            patient.setInsuranceID(resultSet.getString("insuranceMemberID"));
        }
        return patient;
    }

    public ArrayList<Record> writeResultSet(ResultSet resultSet, ArrayList<Record> records) throws SQLException {
        while (resultSet.next()) {
            Record temp = new Record();
            temp.setId(resultSet.getInt("ID"));
            temp.setPatient(new Patient(resultSet.getInt("ID")));
            temp.setServiceDate(resultSet.getDate("recordDate"));
            temp.setDoctor(new User(resultSet.getInt("doctorID")));
            temp.setHeight(resultSet.getString("height"));
            temp.setWeight(resultSet.getString("weight"));
            temp.setBloodPress(resultSet.getString("bloodPressure"));
            temp.setCholest(resultSet.getString("cholesterol"));
            temp.setReason(resultSet.getString("reasonforVisit"));
            temp.setDiagnoses(resultSet.getString("doctorDiagnosis"));
            temp.setDocNotes(resultSet.getString("doctorNote"));
            temp.setLabNotes(resultSet.getString("labNote"));

            records.add(temp);
        }
        return records;
    }

    public SecurityProfile writeResultSet(ResultSet resultSet, SecurityProfile profile) throws SQLException {
        while (resultSet.next()) {
            profile.setId(resultSet.getInt("ID"));
            profile.setAppointmentLvl(resultSet.getInt("appointmentSecurity"));
            profile.setRecordLvl(resultSet.getInt("recordSecurity"));
            profile.setUserManLvl(resultSet.getInt("userManagmentSecurity"));
            profile.setLabManLvl(resultSet.getInt("labManagmentSecurity"));
        }
        return profile;
    }

    public Doctor writeResultSet(ResultSet resultSet, Doctor doctor) throws SQLException {
        while (resultSet.next()) {
            doctor.setDoctorID(resultSet.getInt("ID"));
            doctor.setId(resultSet.getInt("userID"));
            doctor.setMax(resultSet.getInt("maxPatients"));
            doctor.setCurrent(resultSet.getInt("currentPatients"));
        }
        return doctor;
    }

    public User writeResultSet(ResultSet resultSet, User user) throws SQLException {
        while (resultSet.next()) {
            user.setId(resultSet.getInt("ID"));
            user.setFName(resultSet.getString("fname"));
            user.setLName(resultSet.getString("lname"));
            user.setPhone(resultSet.getString("pnumber"));
            user.setSSN(resultSet.getString("ssn"));
            user.setSecurityID(new SecurityProfile(resultSet.getInt("securityID")));
            user.setEmail(resultSet.getString("emailID"));
            user.setPassword(resultSet.getString("userPassword"));
            user.setAddress(resultSet.getString("address"));
        }
        return user;
    }

    public ArrayList<Patient> writeResultPatientsList(ResultSet resultSet) throws SQLException {
        ArrayList<Patient> listOfPatients = new ArrayList<Patient>();
        while (resultSet.next()) {
            User tmpU = new User(resultSet.getInt("userID"));
            Patient tmp = new Patient(tmpU);
            tmp.setPatientID(resultSet.getInt("patientID"));
            tmp.setDob(resultSet.getDate("dob"));
            tmp.setInsuranceProvider(resultSet.getString("insuranceProvider"));
            tmp.setInsuranceID(resultSet.getString("insuranceMemberID"));
            listOfPatients.add(tmp);
        }
        return listOfPatients;
    }

    public ArrayList<Appointment> writeResultApptList(ResultSet resultSet) throws SQLException {
        ArrayList<Appointment> listOfAppts = new ArrayList<Appointment>();
        while (resultSet.next()) {
            Appointment tmp = new Appointment();
            User tmpU = new User(resultSet.getInt("doctorID"));
            Patient tmpP = new Patient();
            tmpP.setPatientID(resultSet.getInt("patientID"));
            tmp.setDoctor(new Doctor(tmpU));
            tmp.setPatient(tmpP);
            tmp.setApptTime(resultSet.getDate("appointmentTime"));
            tmp.setCreatedTime(resultSet.getDate("timeCreated"));
            tmp.setCreator(new User(resultSet.getInt("creatorID")));
            listOfAppts.add(tmp);
        }
        return listOfAppts;
    }

    public ArrayList<Doctor> writeResultDocList(ResultSet resultSet) throws SQLException {
        ArrayList<Doctor> listOfDocs = new ArrayList<Doctor>();
        while (resultSet.next()) {
            Doctor tmp = new Doctor();
            tmp.setDoctorID(resultSet.getInt("ID"));
            tmp.setId(resultSet.getInt("userID"));
            tmp.setMax(resultSet.getInt("maxPatients"));
            tmp.setCurrent(resultSet.getInt("currentPatients"));
            listOfDocs.add(tmp);
        }
        return listOfDocs;
    }
}
