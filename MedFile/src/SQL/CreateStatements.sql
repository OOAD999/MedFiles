create database MedFiles;
create table `MedFiles`.`user` 
(
ID INT(6) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY, 
fname varchar(20),
 lname varchar (20),
 pnumber varchar (10),
 address varchar (70),
 ssn varchar(9),
 securityID int,
 emailID varchar (40),
 userPassword varchar (8)
);
create table `MedFiles`.`securityProfile`
(
ID int UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
appointmentSecurity int,
recordSecurity int,
userManagmentSecurity int,
labManagmentSecurity int
);
create table `MedFiles`.`patient`
(
patientID int UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
userID int(6),
dob date,
insuranceProvider varchar(20),
insuranceMemberID varchar(10)
);

create table `MedFiles`.`appointment`
(
patientID int NOT NULL PRIMARY KEY,
doctorID int NOT NULL UNIQUE KEY,
appointmentTime DATETIME,
timecreated DATETIME,
creatorID int
);

create table `MedFiles`.`record`
(
ID int UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
pateintID int,
recordDate date,
doctorID int,
location varchar(30),
height varchar(10),
weight varchar(10),
bloodPressure varchar(10),
cholesterol varchar(10),
reasonforVisit varchar(250),
doctorDiagnosis varchar (250),
doctorNote varchar (300),
labNote varchar(300)
);

create table `MedFiles`.`doctor`
(
ID int UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
userID int,
maxPatients int,
currentPatients int
CONSTRAINT fk_doctor_userID FOREIGN KEY (userID) 
REFERENCES user(ID)
);

create table `MedFiles`.`doc_patient`
(
ID int UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
doctorID int,
patientID int
CONSTRAINT fk_DP_doctorID FOREIGN KEY (doctorID) 
REFERENCES doctor(ID),
CONSTRAINT fk_DP_patientID FOREIGN KEY (patientID) 
REFERENCES patient(ID)
);

