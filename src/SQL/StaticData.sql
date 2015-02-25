INSERT INTO securityProfile (appointmentSecurity, recordSecurity, userManagmentSecurity)
    VALUES (2, 1, 0)

INSERT INTO user (fname, lname, pnumber, address, ssn, securityID, emailID, userPassword)
    VALUES ('John', 'Smith', '2148675309', '123 Cherry Rd|Dallas|Tx|75080', '147852369', 1, 'a@a.com', '12345678')

INSERT INTO user (fname, lname, pnumber, address, ssn, securityID, emailID, userPassword)
    VALUES ('Jane', 'Smith', '9035768412', '321 Grape Rd|Dallas|Tx|75081', '147852369', 1, 'b@b.com', '87654321')

INSERT INTO patient (userID, dob, insuranceProvider, insuranceMemberID)
    VALUES (1, '1985-10-30', 'Afflack', 'AAA')

