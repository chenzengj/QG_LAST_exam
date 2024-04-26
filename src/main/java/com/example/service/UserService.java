package com.example.service;

import com.example.po.User;

public interface UserService {
    //返回登录状态1
    boolean LoginConfirm(String name,String password);
    String getRole();
    //返回id
    int getId();
    //返回注册状态
    boolean Register(User user);
    //返回学生教师信息编辑保存状态
    boolean StuInfo(String introduction,String studentId,String grade);
    boolean TeaInfo(String introduction,String email,String qq);
    //返回加载教师学生个人信息状态
    String loadStuInfo();
    String loadTeaInfo();
    //返回课程创建状态
    //boolean creatCourse();
}
