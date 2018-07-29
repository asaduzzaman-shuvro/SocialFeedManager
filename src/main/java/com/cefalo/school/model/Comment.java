package com.cefalo.school.model;

import java.util.Date;
import java.util.UUID;

public class Comment{
    public String identifier = "";
    public String text = "";
    public Date publishDate;
    public String commenteDisplayName = "";
    public String commenteId = "";

    public Comment(String identifier, String text, Date publishDate, String commenteDisplayName,
        String commenteId) {
        this.identifier = identifier;
        this.text = text;
        this.publishDate = publishDate;
        this.commenteDisplayName = commenteDisplayName;
        this.commenteId = commenteId;
    }

    public Comment(String text) {
        this.identifier = UUID.randomUUID().toString();
        this.publishDate = new Date();
        this.text = text;
    }
}
