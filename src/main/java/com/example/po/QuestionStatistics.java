package com.example.po;
//存储每个题目的答题统计数据,
//包括答题次数、正确次数和正确率。
//教师可以根据该类了解学生对每道题目的答题情况。
public class QuestionStatistics {
    private int questionId;
    private int totalAttempts;
    private int correctAttempts;
    private double accuracy;

    // 构造函数、Getter和Setter方法

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public int getTotalAttempts() {
        return totalAttempts;
    }

    public void setTotalAttempts(int totalAttempts) {
        this.totalAttempts = totalAttempts;
    }

    public int getCorrectAttempts() {
        return correctAttempts;
    }

    public void setCorrectAttempts(int correctAttempts) {
        this.correctAttempts = correctAttempts;
    }

    public double getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(double accuracy) {
        this.accuracy = accuracy;
    }
}
