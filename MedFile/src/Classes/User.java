/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.sql.SQLException;

public class User {

    private int id;
    private String email;
    private String password;
    private String fname;
    private String lname;
    private String SSN;
    private String phone;
    private String address;
    private SecurityProfile securityID;
    private String table = "user";
    private DBconnect dbo;
    private SearchModule search = new SearchModule();

    public User() {
    }

    public User(int id) {
        this.id = id;
    }

    public User(String email) {
        this.email = email;
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

    public String getAddress() {
        return this.address;
    }

    /**
     * @param address the name to set
     */
    public void setAddress(String address) {
        this.address = address;
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
        return "(" + phone.charAt(0) + phone.charAt(1) + phone.charAt(2) + ") "
                + phone.charAt(3) + phone.charAt(4) + phone.charAt(5) + " - "
                + phone.charAt(6) + phone.charAt(7) + phone.charAt(8) + phone.charAt(9);
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

    public User login(String password) throws SQLException {
        search.searchUser(this);
        if (this.password.equals(password)) {
            return this;
        } else {
            return null;
        }
    }

    public String getDBTable() {
        return this.table;
    }

}
