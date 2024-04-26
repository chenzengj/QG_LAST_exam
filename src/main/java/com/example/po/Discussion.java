package com.example.po;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;
public class Discussion {
    private int id;
    private int courseId;
    private int authorId;
    private User.UserType authorType;
    private String title;
    private String content;
    private Date createdAt;
    private List<Reply> replies = new ArrayList<>();

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

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public User.UserType getAuthorType() {
        return authorType;
    }

    public void setAuthorType(User.UserType authorType) {
        this.authorType = authorType;
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

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public List<Reply> getReplies() {
        return replies;
    }

    public void setReplies(List<Reply> replies) {
        this.replies = replies;
    }

    // 添加回复
    public void addReply(Reply reply) {
        replies.add(reply);
    }

    // 移除回复
    public void removeReply(Reply reply) {
        replies.remove(reply);
    }
}
