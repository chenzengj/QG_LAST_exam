package com.example.service;

import com.example.po.*;

import java.util.Date;
import java.util.List;

public interface CourseService {
    //返回课程创建状态
    boolean creatCourse(String courseName, String description, Date startDate,Date endtDate,int maxStudents);
    //返回创建章节状态
    boolean creatChapter(Chapter chapter);
    //返回课程集合
    List<Course> getAllTeacherCourses();
    //返回题目创建状态
    boolean creatQuestion(Question question);
    //选课状态
    boolean selectCourse(int courseId);
    //获取可学习课程
    List<Course> getLearningCourses();
    //将答题情况存入数据库
    boolean setAnswer(Answer answer);
    //将学习情况存入数据库
    boolean setLearningProgress(LearningProgress learningProgress);
}
