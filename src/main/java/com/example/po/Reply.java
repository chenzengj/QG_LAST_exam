package com.example.po;

import java.util.Date;

public class Reply {
    private int id;
    private int discussionId;
    private int authorId;
    private User.UserType authorType;
    private String content;
    private Date createdAt;

    // 构造函数、Getter和Setter方法

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDiscussionId() {
        return discussionId;
    }

    public void setDiscussionId(int discussionId) {
        this.discussionId = discussionId;
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
}

