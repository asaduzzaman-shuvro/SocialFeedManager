package com.cefalo.school.model;

import java.util.Date;
import java.util.UUID;

public class Comment{
    public String identifier = "";
    public String userId = "";
    public String text = "";
    public Date publishDate;
    public String commenterDisplayName = "";

    public Comment(String identifier, String text, Date publishDate, String userId, String commenter) {
        this.identifier = identifier;
        this.text = text;
        this.publishDate = publishDate;
        this.commenterDisplayName = commenter;
        this.userId = userId;
    }

    public Comment(String text,String userId, String commenter) {
        this.identifier = UUID.randomUUID().toString();
        this.publishDate = new Date();
        this.text = text;
        this.commenterDisplayName = commenter;
        this.userId = userId;
    }
}
