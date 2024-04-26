create database mydb;
use mydb;
# 用于存储用户的基本信息,包括用户ID、用户名、密码等通用属性
CREATE TABLE users (
                       id INT AUTO_INCREMENT PRIMARY KEY,
                       username VARCHAR(50) NOT NULL UNIQUE,
                       password VARCHAR(100) NOT NULL,
                       user_type ENUM('student', 'teacher') NOT NULL
);
#用于存储教师的特有信息,如简介、邮箱等。
#使用user_id字段与User表建立一对一关联
CREATE TABLE teachers (
                          id INT AUTO_INCREMENT PRIMARY KEY,
                          user_id INT UNIQUE NOT NULL,
                          introduction TEXT,
                          email VARCHAR(100),
                            qq VARCHAR(12),
                          FOREIGN KEY (user_id) REFERENCES users(id)
);
# 用于存储学生的特有信息,如学号、年级等。
# 使用user_id字段与User表建立一对一关联。
CREATE TABLE students (
                          id INT AUTO_INCREMENT PRIMARY KEY,
                          user_id INT UNIQUE NOT NULL,
                          student_id VARCHAR(20) UNIQUE NOT NULL,
                          grade INT,
                          introduction TEXT,
                          FOREIGN KEY (user_id) REFERENCES users(id)
);
# 用于存储课程的基本信息。
# 使用teacher_id字段与Teacher表建立一对多关联
CREATE TABLE courses (
                         id INT AUTO_INCREMENT PRIMARY KEY,
                         name VARCHAR(100) NOT NULL,
                         description TEXT,
                         teacher_id INT NOT NULL,
                         start_date DATE,
                         end_date DATE,
                         max_students INT,
                         FOREIGN KEY (teacher_id) REFERENCES teachers(id)
);
# 用于存储课程章节的信息。
# 使用course_id字段与Course表建立一对多关联。
CREATE TABLE chapters (
                          id INT AUTO_INCREMENT PRIMARY KEY,
                          course_id INT NOT NULL,
                          title VARCHAR(100) NOT NULL,
                          content TEXT,
                          FOREIGN KEY (course_id) REFERENCES courses(id)
);
# 用于存储题目的基本信息,作为父表。
# 使用chapter_id字段与Chapter表建立一对多关联。
CREATE TABLE questions (
                           id INT AUTO_INCREMENT PRIMARY KEY,
                           chapter_id INT NOT NULL,
                           question_type varchar(10) NOT NULL,
                           description TEXT NOT NULL,
                           score INT NOT NULL DEFAULT 0,
                            answer varchar(150) not null,
                           FOREIGN KEY (chapter_id) REFERENCES chapters(id)
);
# 用于存储选择题的选项信息,与Question表建立一对一关联。
CREATE TABLE choice_questions (
                                  question_id INT PRIMARY KEY,
                                  option text not null,
                                  FOREIGN KEY (question_id) REFERENCES questions(id)
);
# 用于存储填空题的答案信息,与Question表建立一对一关联
CREATE TABLE fill_blank_questions (
                                      question_id INT PRIMARY KEY,
                                      answer VARCHAR(100) NOT NULL,
                                      FOREIGN KEY (question_id) REFERENCES questions(id)
);
# 用于存储简答题的参考答案信息,与Question表建立一对一关联
CREATE TABLE essay_questions (
                                 question_id INT PRIMARY KEY,
                                 reference_answer TEXT NOT NULL,
                                 FOREIGN KEY (question_id) REFERENCES questions(id)
);
# 用于存储学生对题目的答案记录。
# 使用student_id和question_id字段分别与Student和Question表建立关联。

CREATE TABLE answers (
                         id INT AUTO_INCREMENT PRIMARY KEY,
                         student_id INT NOT NULL,
                         question_id INT NOT NULL,
                         answer TEXT NOT NULL,
                         is_correct BOOLEAN NOT NULL DEFAULT FALSE,
                         FOREIGN KEY (student_id) REFERENCES students(id),
                         FOREIGN KEY (question_id) REFERENCES questions(id)
);
# 用于存储学生选课记录。
# 使用student_id和course_id字段分别与Student和Course表建立关联
CREATE TABLE enrollments (
                             id INT AUTO_INCREMENT PRIMARY KEY,
                             student_id INT NOT NULL,
                             course_id INT NOT NULL,
                             enroll_date DATE NOT NULL,
                             FOREIGN KEY (student_id) REFERENCES students(id),
                             FOREIGN KEY (course_id) REFERENCES courses(id),
                             UNIQUE (student_id, course_id)
);
# 用于存储课程讨论的信息。
# 使用course_id字段与Course表建立关联。
# 使用author_id和author_type字段标识讨论发起者的类型(学生或教师)。
CREATE TABLE discussions (
                             id INT AUTO_INCREMENT PRIMARY KEY,
                             course_id INT NOT NULL,
                             author_id INT NOT NULL,
                             author_type ENUM('student', 'teacher') NOT NULL,
                             title VARCHAR(200) NOT NULL,
                             content TEXT NOT NULL,
                             created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                             FOREIGN KEY (course_id) REFERENCES courses(id)
);
# 用于存储对讨论的回复信息
# 使用discussion_id字段与Discussion表建立关联
# 使用author_id和author_type字段标识回复者的类型(学生或教师)
CREATE TABLE replies (
                         id INT AUTO_INCREMENT PRIMARY KEY,
                         discussion_id INT NOT NULL,
                         author_id INT NOT NULL,
                         author_type ENUM('student', 'teacher') NOT NULL,
                         content TEXT NOT NULL,
                         created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                         FOREIGN KEY (discussion_id) REFERENCES discussions(id)
);
#用于记录学生在每个章节的学习进度,
# 包括答题数量、正确数量和正确率等详细信息。
#教师可以根据课程ID查询该课程中所有学生的学习进度
CREATE TABLE learning_progress (
                                   id INT AUTO_INCREMENT PRIMARY KEY,
                                   student_id INT NOT NULL,
                                   chapter_id INT NOT NULL,
                                   course_id INT NOT NULL,
                                   questions_attempted INT NOT NULL DEFAULT 0,
                                   questions_correct INT NOT NULL DEFAULT 0,
                                   accuracy DOUBLE NOT NULL DEFAULT 0,
                                   FOREIGN KEY (student_id) REFERENCES students(id),
                                   FOREIGN KEY (chapter_id) REFERENCES chapters(id),
                                   FOREIGN KEY (course_id) REFERENCES courses(id) -- 新增外键约束
);
#用于记录学生完成某门课程的情况,包括完成日期和可选的总成绩等信息
CREATE TABLE learning_records (
                                  id INT AUTO_INCREMENT PRIMARY KEY,
                                  student_id INT NOT NULL,
                                  course_id INT NOT NULL,
                                  completed_date DATE NOT NULL,
                                  score DOUBLE,
                                  FOREIGN KEY (student_id) REFERENCES students(id),
                                  FOREIGN KEY (course_id) REFERENCES courses(id)
);
#存储每门课程的整体统计数据
CREATE TABLE course_statistics (
                                   course_id INT PRIMARY KEY,
                                   average_score DOUBLE NOT NULL,
                                   total_students INT NOT NULL,
                                   total_questions INT NOT NULL,
                                   total_questions_attempted INT NOT NULL,
                                   total_questions_correct INT NOT NULL,
                                   overall_accuracy DOUBLE NOT NULL,
                                   FOREIGN KEY (course_id) REFERENCES courses(id)
);
#存储每道题目的答题统计数据
CREATE TABLE question_statistics (
                                     question_id INT PRIMARY KEY,
                                     total_attempts INT NOT NULL,
                                     correct_attempts INT NOT NULL,
                                     accuracy DOUBLE NOT NULL,
                                     FOREIGN KEY (question_id) REFERENCES questions(id)
);
CREATE TABLE choice_questions (
                                  id INT AUTO_INCREMENT PRIMARY KEY,
                                  question_id INT NOT NULL ,
                                  options text not null,
                                  FOREIGN KEY (question_id) REFERENCES questions(id)
);
create table answer_situation(
                                 id INT AUTO_INCREMENT PRIMARY KEY,
                                 question_id INT NOT NULL ,
                                 question_description text not null ,
                                 isCorrect boolean not null,
                                 FOREIGN KEY (question_id) REFERENCES questions(id)


);
ALTER TABLE answer_situation
    ADD CONSTRAINT haha
        FOREIGN KEY (student_id)
            REFERENCES students(id);
-- 创建帖子表
CREATE TABLE posts (
                       id INT AUTO_INCREMENT PRIMARY KEY,
                       title VARCHAR(255) NOT NULL,
                       content TEXT NOT NULL,
                       author VARCHAR(100) NOT NULL,
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 创建评论表
CREATE TABLE comments (
                          id INT AUTO_INCREMENT PRIMARY KEY,
                          post_id INT NOT NULL,
                          content TEXT NOT NULL,
                          author VARCHAR(100) NOT NULL,
                          created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                          FOREIGN KEY (post_id) REFERENCES posts(id) ON DELETE CASCADE
);

