package com.example.javagroup;

import java.sql.*;
import java.time.Year;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Teacher {

    private int tid;
    private String firstName;
    private String lastName;
    public static int count = 1;

    public Teacher(int tid, String firstName, String lastName) {
        setTid(tid);
        setFirstName(firstName);
        setLastName(lastName);
        autoIncrementId();
    }

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        if (tid > 0) {
            this.tid = tid;
        } else {
            System.err.println("Invalid teacher ID. Teacher ID must be greater than 0.");
        }
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        if (firstName != null && !firstName.trim().isEmpty()) {
            this.firstName = firstName;
        } else {
            System.err.println("Invalid first name. First name cannot be null or empty.");
        }
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        if (lastName != null && !lastName.trim().isEmpty()) {
            this.lastName = lastName;
        } else {
            System.err.println("Invalid last name. Last name cannot be null or empty.");
        }
    }

    public static void autoIncrementId(){
        count++;
    }

    public static void resetId(){
        count = 1;
    }

    @Override
    public String toString() {
        return tid + ", " + firstName + " " + lastName;
    }

    // MySQL Database ==========================================

    static String dbURL = "jdbc:mysql://localhost:3306/COMP1011AS4";
    static String userName = "root";
    static String password = "root";

    // Fetch Database
    public static List<Teacher> getTeacherFromDB(){

        List<Teacher> teacherList = new ArrayList<Teacher>() {
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
                    "SELECT * FROM Teachers");


            int tid;
            String firstName;
            String lastName;

            while (resultSet.next()) {

                tid = resultSet.getInt("tid");
                firstName = resultSet.getString("firstName");
                lastName = resultSet.getString("lastName");

                teacherList.add(new Teacher(tid, firstName, lastName));
            }
            resultSet.close();
            statement.close();
            connection.close();
        }
        catch (Exception exception) {
            System.out.println(exception);
        }

        return teacherList;
    }


    public static List<List<Object>> getTeacherCoursesFromDB(int tid){
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
                    "SELECT * FROM Courses WHERE tid = " + tid);


            String cid;
//            String courseName;
            String validSeason;
            Year academicYear;

            while (resultSet.next()) {

                cid = resultSet.getString("cid");
//                courseName = resultSet.getString("courseName");
                validSeason = resultSet.getString("academicSeason");
                academicYear = Year.of(resultSet.getInt("academicYear"));

                List<Object> tempList = Arrays.asList(cid, validSeason, academicYear);
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

    public static boolean createTeacherToDB(Teacher teacher) {
        Connection connection = null;
        try {
            // below two lines are used for connectivity.
            Class.forName("com.mysql.cj.jdbc.Driver");
            // change the connection if pull to local
            connection = DriverManager.getConnection(
                    dbURL, userName, password);

            String query = "INSERT INTO Teachers VALUES(?,?,?)";
            PreparedStatement preparedStmt = connection.prepareStatement(query);

            preparedStmt.setString   (1, String.valueOf(teacher.getTid()));
            preparedStmt.setString(2, teacher.getFirstName());
            preparedStmt.setString(3, teacher.getLastName());

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

    public static boolean updateTeacherToDB(Teacher teacher){
        Connection connection = null;
        try {
            // below two lines are used for connectivity.
            Class.forName("com.mysql.cj.jdbc.Driver");
            // change the connection if pull to local
            connection = DriverManager.getConnection(
                    dbURL, userName, password);

            String query = "UPDATE Teachers SET firstName = ?, lastName = ? WHERE tid = ?";
            PreparedStatement preparedStmt = connection.prepareStatement(query);

            preparedStmt.setString(1, teacher.getFirstName());
            preparedStmt.setString(2, teacher.getLastName());
            preparedStmt.setString   (3, String.valueOf(teacher.getTid()));

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


}
