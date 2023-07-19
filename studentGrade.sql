DROP DATABASE IF EXISTS COMP1011AS4;
CREATE DATABASE COMP1011AS4;

USE COMP1011AS4;


DROP TABLE IF EXISTS Students;
CREATE TABLE Students (
    sid INT(9) AUTO_INCREMENT PRIMARY KEY,
    firstName VARCHAR(50) NOT NULL,
    lastName VARCHAR(50) NOT NULL,
    program VARCHAR(30) NOT NULL,
    intakeYear YEAR,
    intakeSeason ENUM('Winter', 'Fall', 'Summer'),
    graduateYear YEAR
);

DROP TABLE IF EXISTS Teachers;
CREATE TABLE Teachers (
    tid INT(9) AUTO_INCREMENT PRIMARY KEY,
    firstName VARCHAR(50) NOT NULL,
    lastName VARCHAR(50) NOT NULL
);

DROP TABLE IF EXISTS Courses;
CREATE TABLE Courses (
    code CHAR(16) PRIMARY KEY,
    courseName VARCHAR(100) NOT NULL,
    tid INT NOT NULL,
    academicSeason ENUM('Winter', 'Fall', 'Summer'),
    academicYear YEAR,
    FOREIGN KEY (tid)
        REFERENCES Teachers (tid)
);

DROP TABLE IF EXISTS AcademicRecords;
CREATE TABLE AcademicRecords (
    recordId INT AUTO_INCREMENT PRIMARY KEY,
    sid INT(9) NOT NULL,
    FOREIGN KEY (sid)
        REFERENCES Students (sid),
    code CHAR(16) NOT NULL,
    FOREIGN KEY (code)
        REFERENCES Courses (code),
    grade INT NOT NULL
);