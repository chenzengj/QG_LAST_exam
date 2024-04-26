package com.example.po;

import java.util.ArrayList;
import java.util.List;


public class Student extends User {
    private String studentId;
    private String grade;
    private String introduction;
    private List<Course> enrolledCourses = new ArrayList<>();

    // 构造函数、Getter和Setter方法

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public List<Course> getEnrolledCourses() {
        return enrolledCourses;
    }

    public void setEnrolledCourses(List<Course> enrolledCourses) {
        this.enrolledCourses = enrolledCourses;
    }

    // 选修课程
    public void enrollCourse(Course course) {
        enrolledCourses.add(course);
    }

    // 退选课程
    public void unenrollCourse(Course course) {
        enrolledCourses.remove(course);
    }
}

