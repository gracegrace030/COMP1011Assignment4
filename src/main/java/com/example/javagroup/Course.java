package com.example.javagroup;

import java.time.Year;
import java.util.List;

public class Course {
    private String cid;
    private String courseName;
    private int tid;
    public enum seasonEnum {
        FALL, WINTER, SUMMER
    };
    private seasonEnum season;
    private Year academicYear;

    private List<Teacher> teacherList;

    // Constructor ==========================================


    public Course(String cid, String courseName, int tid, String validSeason, Year academicYear) {
        setCid(cid);
        setCourseName(courseName);
        this.tid = tid;
//        setTid(tid);
        setSeason(validSeason);
        setAcademicYear(academicYear);
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

    @Override
    public String toString() {
        return cid + ", " +  courseName;
    }
}
