package com.example.javagroup;

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
        return lastName + ", " +  firstName;
    }
}
