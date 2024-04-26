package com.example.po;


import java.util.ArrayList;
import java.util.List;

public class Question {
    private int id;
    private int chapterId;
    private String questionType;
    private String description;
    private int score;
    private String answer;
    public List<String> options=new ArrayList<>();
    public String getAnswer() {
        return answer;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
// 构造函数、Getter和Setter方法

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getChapterId() {
        return chapterId;
    }

    public void setChapterId(int chapterId) {
        this.chapterId = chapterId;
    }



    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getQuestionType() {
        return questionType;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }
    public void addOptions(String option){
        options.add(option);
    }
}