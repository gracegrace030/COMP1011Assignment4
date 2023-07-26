package com.example.javagroup;

import java.sql.*;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

public class Course {
    private String cid;
    private String courseName;
    private int tid;
    private String tFirstName;
    private String tLastName;
    public enum seasonEnum {
        FALL, WINTER, SUMMER
    };
    private seasonEnum season;
    private Year academicYear;

    private List<Teacher> teacherList;
    public static int count = 0;

    // Constructor ==========================================

    // For Display
    public Course(String cid, String courseName, int tid, String tFirstName, String tLastName, String validSeason, Year academicYear) {
        setCid(cid);
        setCourseName(courseName);
        this.tid = tid;
        settFirstName(tFirstName);
        settLastName(tLastName);
        setSeason(validSeason);
        setAcademicYear(academicYear);
        autoIncrementId();
    }

    // For User creates
    public Course(String cid, String courseName, int tid, String season, Year academicYear) {
        setCid(cid);
        setCourseName(courseName);
        this.tid = tid;
        setSeason(season);
        setAcademicYear(academicYear);
        autoIncrementId();
    }

    // Getters and Setters ==========================================
    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        boolean hasTid = false;
        for (Teacher teacher : teacherList){
            if (teacher.getTid() == tid){
                this.tid = tid;
                hasTid = true;
                break;
            }
        }
        if (!hasTid){
            System.err.println("This teacher isn't in the teacher list.");
        }
    }

    public String gettFirstName() {
        return tFirstName;
    }

    public void settFirstName(String tFirstName) {
        this.tFirstName = tFirstName;
    }

    public String gettLastName() {
        return tLastName;
    }

    public void settLastName(String tLastName) {
        this.tLastName = tLastName;
    }

    public seasonEnum getSeason() {
        return season;
    }

    public void setSeason(String season) {
        String validSeason = season.trim().toUpperCase();
        for (seasonEnum item : seasonEnum.values()) {
            if (item.toString().equals(validSeason)){
                this.season = seasonEnum.valueOf(validSeason);
                break;
            }
        }
        if(this.season == null){
            System.err.println("Invalid season. Please select a valid season");
        }
    }

    public Year getAcademicYear() {
        return academicYear;
    }

    public void setAcademicYear(Year academicYear) {
        this.academicYear = academicYear;
    }

    // Methods

    public static void autoIncrementId(){
        count++;
    }

    public static void resetId(){
        count = 0;
    }

    // MySQL Database ==========================================

    static String dbURL = "jdbc:mysql://localhost:3306/COMP1011AS4";
    static String userName = "root";
    static String password = "root";

    // Fetch Database
    public static List<Course> getCourseFromDB(){

        List<Course> courseList = new ArrayList<Course>() {
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
                    "SELECT * FROM Courses LEFT JOIN Teachers ON Courses.tid = Teachers.tid");

            String cid;
            String courseName;
            int tid;
            String tFirstName;
            String tLastName;
            String validSeason;
            Year academicYear;

            while (resultSet.next()) {

                cid = resultSet.getString("cid");
                courseName = resultSet.getString("courseName");
                tid = resultSet.getInt("tid");
                tFirstName = resultSet.getString("firstName");
                tLastName = resultSet.getString("lastName");
                validSeason = resultSet.getString("academicSeason");
                academicYear = Year.of(resultSet.getInt("academicYear"));

                courseList.add(new Course(cid, courseName, tid, tFirstName, tLastName, validSeason, academicYear));
            }
            resultSet.close();
            statement.close();
            connection.close();
        }
        catch (Exception exception) {
            System.out.println(exception);
        }

        return courseList;
    }

    public static void updateCourseToDB(Course course){
        Connection connection = null;
        try {
            // below two lines are used for connectivity.
            Class.forName("com.mysql.cj.jdbc.Driver");
            // change the connection if pull to local
            connection = DriverManager.getConnection(
                    dbURL, userName, password);

            String query = "INSERT INTO Courses VALUES(?,?,?,?,?)";
            PreparedStatement preparedStmt = connection.prepareStatement(query);

            preparedStmt.setString   (1, course.getCid());
            preparedStmt.setString(2, course.getCourseName());
            preparedStmt.setInt   (3, course.getTid());
            preparedStmt.setString(4, String.valueOf(course.getSeason()));
            preparedStmt.setInt(5, Integer.parseInt(String.valueOf(course.getAcademicYear())));

            // execute the java preparedstatement
            preparedStmt.executeUpdate();

            connection.close();
        }
        catch (Exception exception) {
            System.out.println(exception);
        }
    }

    @Override
    public String toString() {
        return cid + ", " +  courseName;
    }
}
