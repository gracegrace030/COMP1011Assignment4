package com.example.javagroup;

public class Student {
    private int sid;
    private String firstName;
    private String lastName;
    private String program;
    private int intakeYear;
    private String intakeSeason;
    private int graduateYear;

    // Constructor
    public Student(int sid, String firstName, String lastName, String program, int intakeYear, String intakeSeason, int graduateYear) {
        this.sid = sid;
        this.firstName = firstName;
        this.lastName = lastName;
        this.program = program;
        this.intakeYear = intakeYear;
        this.intakeSeason = intakeSeason;
        this.graduateYear = graduateYear;
    }

    // Getters and Setters
    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public int getIntakeYear() {
        return intakeYear;
    }

    public void setIntakeYear(int intakeYear) {
        this.intakeYear = intakeYear;
    }

    public String getIntakeSeason() {
        return intakeSeason;
    }

    public void setIntakeSeason(String intakeSeason) {
        this.intakeSeason = intakeSeason;
    }

    public int getGraduateYear() {
        return graduateYear;
    }

    public void setGraduateYear(int graduateYear) {
        this.graduateYear = graduateYear;
    }
}
