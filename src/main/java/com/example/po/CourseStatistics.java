package com.example.po;
//存储某门课程的整体统计数据,
//包括平均分、学生人数、题目数量、答题情况等。
//教师可以通过该类获取课程的总体学习情况。
public class CourseStatistics {
    private int courseId;
    private double averageScore;
    private int totalStudents;
    private int totalQuestions;
    private int totalQuestionsAttempted;
    private int totalQuestionsCorrect;
    private double overallAccuracy;

    // 构造函数、Getter和Setter方法

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public double getAverageScore() {
        return averageScore;
    }

    public void setAverageScore(double averageScore) {
        this.averageScore = averageScore;
    }

    public int getTotalStudents() {
        return totalStudents;
    }

    public void setTotalStudents(int totalStudents) {
        this.totalStudents = totalStudents;
    }

    public int getTotalQuestions() {
        return totalQuestions;
    }

    public void setTotalQuestions(int totalQuestions) {
        this.totalQuestions = totalQuestions;
    }

    public int getTotalQuestionsAttempted() {
        return totalQuestionsAttempted;
    }

    public void setTotalQuestionsAttempted(int totalQuestionsAttempted) {
        this.totalQuestionsAttempted = totalQuestionsAttempted;
    }

    public int getTotalQuestionsCorrect() {
        return totalQuestionsCorrect;
    }

    public void setTotalQuestionsCorrect(int totalQuestionsCorrect) {
        this.totalQuestionsCorrect = totalQuestionsCorrect;
    }

    public double getOverallAccuracy() {
        return overallAccuracy;
    }

    public void setOverallAccuracy(double overallAccuracy) {
        this.overallAccuracy = overallAccuracy;
    }
}

