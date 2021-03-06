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

/**
 *
 * @author softwareProject
 */
public class User {
    private int id;
    private String email;
    private String password;
    private String fname;
    private String lname;
    private String SSN;
    private String phone;
    private String addr1;
    private String addr2;
    private String city;
    private String state;
    private String zip;
    private SecurityProfile securityID;
    private String table = "user";

    public User() {
    }
    
    public User(int id) {
        this.id = id;
    }
    public User(String email, String password) {
        this.email = email;
        this.password = password;
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
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the name
     */
    public String getFName() {
        return fname;
    }

    /**
     * @param name the name to set
     */
    public void setFName(String name) {
        this.fname = name;
    }
    
        /**
     * @return the name
     */
    public String getLName() {
        return lname;
    }

    /**
     * @param name the name to set
     */
    public void setLName(String name) {
        this.lname = name;
    }

    /**
     * @return the SSN
     */
    public String getSSN() {
        return SSN;
    }
    
    /**
     * @return the SSN
     */
    public String getMaskSSN() {
        return "***-**-" + SSN.charAt(SSN.length() - 4) + SSN.charAt(SSN.length() - 3) 
                + SSN.charAt(SSN.length() - 2) + SSN.charAt(SSN.length() - 1);

    }

    /**
     * @param SSN the SSN to set
     */
    public void setSSN(String SSN) {
        this.SSN = SSN;
    }

    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }
    
    /**
     * @return the phone
     */
    public String getFormatPhone() {
        return "(" + phone.charAt(0) + phone.charAt(1) + phone.charAt(2) + ") " +
                phone.charAt(3) + phone.charAt(4) + phone.charAt(5) + " - " +
                phone.charAt(6) + phone.charAt(7) + phone.charAt(8) + phone.charAt(9);
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return the securityID
     */
    public SecurityProfile getSecurityID() {
        return securityID;
    }

    /**
     * @param securityID the securityID to set
     */
    public void setSecurityID(SecurityProfile securityID) {
        this.securityID = securityID;
    }

    /**
     * @return the addr1
     */
    public String getAddr1() {
        return addr1;
    }

    /**
     * @param addr1 the addr1 to set
     */
    public void setAddr1(String addr1) {
        this.addr1 = addr1;
    }
    
    /**
     * @return the addr2
     */
    public String getAddr2() {
        return addr2;
    }

    /**
     * @param addr2 the addr2 to set
     */
    public void setAddr2(String addr2) {
        this.addr2 = addr2;
    }

    /**
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city the city to set
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @return the state
     */
    public String getState() {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * @return the zip
     */
    public String getZip() {
        return zip;
    }

    /**
     * @param zip the zip to set
     */
    public void setZip(String zip) {
        this.zip = zip;
    }
    
    /**
     * @return the full address
     */
    public String getFullAddress() {
        return this.getAddr1() + " " + this.getAddr2() + " " + 
                this.getCity() + " " + this.getState() + " " + 
                this.getZip();
    }

    public User selectUser() throws SQLException {
        DBconnect dbo = new DBconnect();       
        dbo.connect();
        PreparedStatement query = dbo.getCon().prepareStatement("SELECT * FROM " + dbo.getDbName() + "." + table
            + " WHERE ID = ?");
        query.setInt(1, this.id);
        ResultSet results = dbo.select(query);
        writeResultSet(results);
        dbo.disconnect();
        return this;
    }
    
    private void writeResultSet(ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            this.id = resultSet.getInt("ID");
            this.fname = resultSet.getString("fname");
            this.lname = resultSet.getString("lname");
            this.phone = resultSet.getString("pnumber");
            this.SSN = resultSet.getString("ssn");
            this.securityID = new SecurityProfile(resultSet.getInt("securityID"));
            this.email = resultSet.getString("emailID");
            this.password = resultSet.getString("userPassword");
            
            String[] address = resultSet.getString("address").split("|");
            this.addr1 = address[0];
            this.city = address[1];
            this.state = address[2];
            this.zip = address[3];
        }
    }
}
