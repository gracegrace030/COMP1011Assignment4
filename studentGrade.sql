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
    cid CHAR(16) PRIMARY KEY,
    courseName VARCHAR(100) NOT NULL,
    tid INT(9) NOT NULL,
    academicSeason ENUM('Winter', 'Fall', 'Summer'),
    academicYear YEAR,
    FOREIGN KEY (tid)
        REFERENCES Teachers (tid)
);

DROP TABLE IF EXISTS Student_Courses;
CREATE TABLE Student_Courses (
	sid INT(9) NOT NULL,
	FOREIGN KEY (sid)
        REFERENCES Students (sid),
    cid CHAR(16)NOT NULL,
    FOREIGN KEY (cid)
        REFERENCES Courses (cid)
);

DROP TABLE IF EXISTS AcademicRecords;
CREATE TABLE AcademicRecords (
    rid INT AUTO_INCREMENT PRIMARY KEY,
    sid INT(9) NOT NULL,
    FOREIGN KEY (sid)
        REFERENCES Students (sid),
    cid CHAR(16) NOT NULL,
    FOREIGN KEY (cid)
        REFERENCES Courses (cid),
	
    grade DECIMAL(4,1) NOT NULL
);

-- ============================================
-- Dummy data for testing

INSERT INTO
	Students(firstName, lastName, program, intakeYear, intakeSeason, graduateYear)
VALUE
	("Abby", "Slater", "Computer Programming", 2020, "Winter", 2023),
    ("Johnny", "Edwards", "Business Administration", 2022, "Fall", 2023);

INSERT INTO
	Teachers(firstName, lastName)
VALUE
	("Patrick", "Star"),
    ("Sponge", "Bob");

INSERT INTO
	Courses(cid, courseName, tid, academicSeason, academicYear)
VALUE
	("COMP100022F30987", "Object Oriented Programming", 2, "Fall", 2022),
    ("MATH100223W10001", "Mathematics of Finance", 1, "Winter", 2023);
    
INSERT INTO
	Student_Courses(sid, cid)
VALUE
	(1, "COMP100022F30987"),
    (2, "MATH100223W10001");
    
INSERT INTO
	AcademicRecords(sid, cid, grade)
VALUE
	(1, "COMP100022F30987",90.6),
    (2, "MATH100223W10001",100);
    
TABLE Students;
TABLE Teachers;
TABLE Courses;
TABLE Student_Courses;
TABLE AcademicRecords;