package com.example.po;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Course {
    private int id;
    private String name;
    private String description;
    private String teacher;
    private int teacherId;

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    private Date startDate;
    private Date endDate;
    private int maxStudents;
    private List<Chapter> chapters = new ArrayList<>();
    private List<Student> enrolledStudents = new ArrayList<>();

    // 构造函数、Getter和Setter方法
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getMaxStudents() {
        return maxStudents;
    }

    public void setMaxStudents(int maxStudents) {
        this.maxStudents = maxStudents;
    }

    public List<Chapter> getChapters() {
        return chapters;
    }

    public void setChapters(List<Chapter> chapters) {
        this.chapters = chapters;
    }

    public List<Student> getEnrolledStudents() {
        return enrolledStudents;
    }

    public void setEnrolledStudents(List<Student> enrolledStudents) {
        this.enrolledStudents = enrolledStudents;
    }

    // 添加章节
    public void addChapter(Chapter chapter) {
        chapters.add(chapter);
    }

    // 移除章节
    public void removeChapter(Chapter chapter) {
        chapters.remove(chapter);
    }

    // 学生选修该课程
    public void enrollStudent(Student student) {
        enrolledStudents.add(student);
    }

    // 学生退选该课程
    public void unenrollStudent(Student student) {
        enrolledStudents.remove(student);
    }
}
