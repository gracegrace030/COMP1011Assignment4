package com.example.javagroup;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

public class Teacher {

    private int tid;
    private String firstName;
    private String lastName;

    public Teacher(int tid, String firstName, String lastName) {
        this.tid = tid;
        this.firstName = firstName;
        this.lastName = lastName;
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

    @Override
    public String toString() {
        return tid + ", " + lastName + " " + firstName;
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
}
