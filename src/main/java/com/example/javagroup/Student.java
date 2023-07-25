package com.example.javagroup;

import java.util.Arrays;
import java.util.List;

public class Student {
    private int sid;
    private String firstName;
    private String lastName;
    private String program;
    private int intakeYear;
    public enum intakeSeasonEnum { // enum or ArrayList?
        FALL, WINTER, SUMMER
    };
    private intakeSeasonEnum intakeSeason;
    private int graduateYear;

    // Add Error Messages ArrayList later

    // Constructor ==========================================
    public Student(int sid, String firstName, String lastName, String program, int intakeYear, intakeSeasonEnum intakeSeason, int graduateYear) {
        this.sid = sid;
        this.firstName = firstName;
        this.lastName = lastName;
        this.program = program;
        this.intakeYear = intakeYear;
        this.intakeSeason = intakeSeason;
        this.graduateYear = graduateYear;
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
        List<String> validProgramList = validProgramList();
        String validProgram = program.trim().toLowerCase();
        if (validProgramList.contains(program)){
            this.program = program;
        }
        else {
            System.err.println("Invalid program. Please select a valid program");
        }
    }

    public static List<String> validProgramList(){
        // fetch valid programs from DB
        return Arrays.asList(
                "Program A",
                "Program B",
                "Program C"
        );
    }

    public int getIntakeYear() {
        return intakeYear;
    }

    public void setIntakeYear(int intakeYear) {
        this.intakeYear = intakeYear;
    }

    public intakeSeasonEnum getIntakeSeason() {
        return intakeSeason;
    }

    public void setIntakeSeason(intakeSeasonEnum intakeSeason) {
        this.intakeSeason = intakeSeason;
    }

    public int getGraduateYear() {
        return graduateYear;
    }

    public void setGraduateYear(int graduateYear) {
        this.graduateYear = graduateYear;
    }

    // Database ==========================================


}
