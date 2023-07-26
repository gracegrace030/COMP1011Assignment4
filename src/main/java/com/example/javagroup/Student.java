package com.example.javagroup;

import java.time.Year;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.sql.*;

public class Student {
    private int sid;
    private String firstName;
    private String lastName;
    private String program;
    private Year intakeYear;
    public enum intakeSeasonEnum {
        FALL, WINTER, SUMMER
    };
    private intakeSeasonEnum intakeSeason;
    private Year graduateYear;
    public static int count = 1;

    // Constructor ==========================================
    public Student(int sid, String firstName, String lastName, String program, Year intakeYear, String validIntakeSeason, Year graduateYear) {
        setSid(sid);
        setFirstName(firstName);
        setLastName(lastName);
        setProgram(program);
        setIntakeYear(intakeYear);
        setIntakeSeason(validIntakeSeason);
        setGraduateYear(graduateYear);
        autoIncrementId();
    }

    // Getters and Setters ==========================================
    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        if (sid > 0) {
            this.sid = sid;
        } else {
            System.err.println("Invalid student ID. Student ID must be greater than 0.");
        }
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        String validFirstName = firstName.trim();
        if (validFirstName.length() > 1){
            this.firstName = validFirstName;
        }
        else {
            System.err.println("Invalid first name. First name must has more than 1 character.");
        }
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        String validLastName = lastName.trim();
        if (validLastName.length() > 1){
            this.lastName = validLastName;
        }
        else {
            System.err.println("Invalid last name. Last name must has more than 1 character.");
        }
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
//        List<String> validProgramList = validProgramList();
//        String validProgram = program.trim().toLowerCase();
        this.program = program;
//        if (validProgramList.contains(program)){
//            this.program = program;
//        }
//        else {
//            System.err.println("Invalid program. Please select a valid program");
//        }
    }

    public Year getIntakeYear() {
        return intakeYear;
    }

    public void setIntakeYear(Year intakeYear) {
        this.intakeYear = intakeYear;
    }

    public intakeSeasonEnum getIntakeSeason() {
        return intakeSeason;
    }

    public void setIntakeSeason(String intakeSeason) {
        String validIntakeSeason = intakeSeason.trim().toUpperCase();
        // if (intakeSeasonEnum.valueOf(validIntakeSeason).equals(validIntakeSeason)){
        for (intakeSeasonEnum season : intakeSeasonEnum.values()) {
            if (season.toString().equals(validIntakeSeason)){
                this.intakeSeason = intakeSeasonEnum.valueOf(validIntakeSeason);
                break;
            }
        }
        if(this.intakeSeason == null){
            System.err.println("Invalid season. Please select a valid season");
        }
    }


    public Year getGraduateYear() {
        return graduateYear;
    }

    public void setGraduateYear(Year graduateYear) {
        this.graduateYear = graduateYear;
    }

    public static void autoIncrementId(){
        count++;
    }

    public static void resetId(){
        count = 1;
    }

    // MySQL Database ==========================================

    static String dbURL = "jdbc:mysql://localhost:3306/COMP1011AS4";
    static String userName = "root";
    static String password = "root";

    public static List<Student> getStudentFromDB(){

        List<Student> studentList = new ArrayList<Student>() {
        };
        Connection connection = null;
        try {
            // below two lines are used for connectivity.
            Class.forName("com.mysql.cj.jdbc.Driver");
            // change the connection if pull to local
            connection = DriverManager.getConnection(
                    dbURL, userName, password);

            Statement statement;
            statement = connection.createStatement();
            ResultSet resultSet;
            resultSet = statement.executeQuery(
                    "select * from Students");

            int sid;
            String firstName;
            String lastName;
            String program;
            Year intakeYear;
            String intakeSeason;
            Year graduateYear;

            while (resultSet.next()) {
                sid = resultSet.getInt("sid");
                firstName = resultSet.getString("firstName");
                lastName = resultSet.getString("lastName");
                program = resultSet.getString("program");
                intakeYear = Year.of(resultSet.getInt("intakeYear"));
                intakeSeason = resultSet.getString("intakeSeason");
                graduateYear = Year.of(resultSet.getInt("graduateYear"));

                studentList.add(new Student(sid, firstName, lastName, program, intakeYear, intakeSeason, graduateYear));
            }
            resultSet.close();
            statement.close();
            connection.close();
        }
        catch (Exception exception) {
            System.out.println(exception);
        }

        return studentList;
    }

    public static List<List<Object>> getStudentCoursesFromDB(int sid){
        List<List<Object>> courseInfo = new ArrayList<List<Object>>();
        Connection connection = null;
        try {
            // below two lines are used for connectivity.
            Class.forName("com.mysql.cj.jdbc.Driver");
            // change the connection if pull to local
            connection = DriverManager.getConnection(
                    dbURL, userName, password);

            Statement statement;
            statement = connection.createStatement();
            ResultSet resultSet;
            resultSet = statement.executeQuery(
                    "SELECT * FROM AcademicRecords LEFT JOIN Courses ON (AcademicRecords.cid = Courses.cid) WHERE AcademicRecords.sid = " + sid);


            String cid;
            String validSeason;
            Year academicYear;
            float grade;

            while (resultSet.next()) {

                cid = resultSet.getString("cid");
//                courseName = resultSet.getString("courseName");
                validSeason = resultSet.getString("academicSeason");
                academicYear = Year.of(resultSet.getInt("academicYear"));
                grade = resultSet.getFloat("grade");

                List<Object> tempList = Arrays.asList(cid, validSeason, academicYear, grade);
                courseInfo.add(tempList);
            }
            resultSet.close();
            statement.close();
            connection.close();
        }
        catch (Exception exception) {
            System.out.println(exception);
        }

        return courseInfo;
    }

    public static boolean createStudentToDB(Student student) {
        Connection connection = null;
        try {
            // below two lines are used for connectivity.
            Class.forName("com.mysql.cj.jdbc.Driver");
            // change the connection if pull to local
            connection = DriverManager.getConnection(
                    dbURL, userName, password);

            String query = "INSERT INTO Students VALUES(?,?,?,?,?,?,?)";
            PreparedStatement preparedStmt = connection.prepareStatement(query);

            // Student(sid, firstName, lastName, program, intakeYear, validIntakeSeason, graduateYear)
            preparedStmt.setInt(1, student.getSid());
            preparedStmt.setString(2, student.getFirstName());
            preparedStmt.setString(3, student.getLastName());
            preparedStmt.setString(4, student.getProgram());
            preparedStmt.setString(5, String.valueOf(student.getIntakeYear()));
            preparedStmt.setString(6, student.getIntakeSeason().name());
            preparedStmt.setString(7, String.valueOf(student.getGraduateYear()));

            // execute the java preparedstatement
            preparedStmt.executeUpdate();

            connection.close();
            return true;
        }
        catch (Exception exception) {
            System.out.println(exception);
            return false;
        }
    }


    @Override
    public String toString() {
        return sid + ", " + firstName + " " + lastName;
    }

}
