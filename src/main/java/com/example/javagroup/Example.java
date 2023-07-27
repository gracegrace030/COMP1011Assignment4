package com.example.javagroup;

import java.time.Year;

public class Example {
    public static void main(String[] args) {
//        System.out.println(Student.getStudentFromDB());
//        Student.getStudentFromDB().forEach(System.out::println);
//        String text = "012345678";
//        System.out.println(text.charAt(4));

        Course tryCourse = new Course("COMP1011222","222Class", 2, "Winter", Year.of(2111));
        System.out.println(tryCourse);
    }
}
