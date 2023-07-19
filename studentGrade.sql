DROP DATABASE IF EXISTS COMP1011AS4;
CREATE DATABASE COMP1011AS4;

USE COMP1011AS4;


DROP TABLE IF EXISTS Students;
CREATE TABLE Students(
    sid int(9) AUTO_INCREMENT PRIMARY KEY,
    firstName varchar(50) NOT NULL,
    lastName varchar(50) NOT NULL,
    program varchar(30)NOT NULL,
    intakeYear YEAR,
    intakeSeason enum("Winter", "Fall", "Summer"),
    graduateYear year
);

DROP TABLE IF EXISTS Teachers;
CREATE TABLE Teachers(
    tid int(9) AUTO_INCREMENT PRIMARY KEY,
    firstName varchar(50) NOT NULL,
    lastName varchar(50) NOT NULL
    -- department varchar(30)NOT NULL
);

DROP TABLE IF EXISTS Courses;
CREATE TABLE Courses(
    code char(16) PRIMARY KEY, -- eg. COMP1011-23S-101
    courseName varchar(100) NOT NULL,
    tid int NOT NULL,
    -- department varchar(50) NOT NULL,
    academicSeason ENUM("Winter", "Fall", "Summer"),
    academicYear year,
    FOREIGN KEY (tid) REFERENCES Teachers(tid)
);

DROP TABLE IF EXISTS AcademicRecords;
CREATE TABLE AcademicRecords(
	recordId INT AUTO_INCREMENT PRIMARY KEY,
    sid int(9) NOT NULL,
    FOREIGN KEY (sid) REFERENCES Students(sid),
    code char(16) NOT NULL,
    FOREIGN KEY (code) REFERENCES Courses(code),
    grade int NOT NULL
);