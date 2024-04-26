package com.example.po;

import java.util.ArrayList;
import java.util.List;


public class Chapter {
    private int id;
    private int courseId;
    private String title;
    private String content;
    private List<Question> questions = new ArrayList<>();

    // 构造函数、Getter和Setter方法

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    // 添加题目
    public void addQuestion(Question question) {
        questions.add(question);
    }

    // 移除题目
    public void removeQuestion(Question question) {
        questions.remove(question);
    }
}

