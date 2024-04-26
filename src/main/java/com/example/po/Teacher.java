package com.example.po;

import java.util.ArrayList;
import java.util.List;


public class Teacher extends User {
    private String introduction;
    private String email;
    private String qq;
    private List<Course> courses = new ArrayList<>();

    // 构造函数、Getter和Setter方法

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getEmail() {
        return email;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    // 添加课程
    public void addCourse(Course course) {
        courses.add(course);
    }

    // 移除课程
    public void removeCourse(Course course) {
        courses.remove(course);
    }
}

//public class Teacher {
//    private int id;
//    private String name;
//    private String email;
//    private String password;
//    private String introduction;
//    private String qq;
//
//    public String getQq() {
//        return qq;
//    }
//
//    public void setQq(String qq) {
//        this.qq = qq;
//    }
//// 构造函数、getter/setter方法
//
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public String getIntroduction() {
//        return introduction;
//    }
//
//    public void setIntroduction(String introduction) {
//        this.introduction = introduction;
//    }
//
//
//    // 重写toString()方法
//
//
//    @Override
//    public String toString() {
//        return "Teacher{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                ", email='" + email + '\'' +
//                ", password='" + password + '\'' +
//                ", introduction='" + introduction + '\'' +
//                ", qq='" + qq + '\'' +
//                '}';
//    }
//}

