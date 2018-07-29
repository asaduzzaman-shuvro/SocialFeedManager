package com.cefalo.school.model;

import java.sql.DatabaseMetaData;
import java.util.Date;
import java.util.UUID;

public class Comment{
    public String identifier = "";
    public String text = "";
    public Date publishDate;
    public String commenteDisplayName = "";

    public Comment(String identifier, String text, Date publishDate, String commenter) {
        this.identifier = identifier;
        this.text = text;
        this.publishDate = publishDate;
        this.commenteDisplayName = commenter;
    }

    public Comment(String text) {
        this.identifier = UUID.randomUUID().toString();
        this.publishDate = new Date();
        this.text = text;
    }
}
