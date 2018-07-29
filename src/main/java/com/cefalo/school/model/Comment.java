package com.cefalo.school.model;

import java.sql.DatabaseMetaData;
import java.util.Date;
import java.util.UUID;

public class Comment{
    public String identifier = "";
    public String text = "";
    public Date publishDate;

    public Comment(String identifier, String text, Date publishDate) {
        this.identifier = identifier;
        this.text = text;
        this.publishDate = publishDate;
    }

    public Comment(String text) {
        this.identifier = UUID.randomUUID().toString();
        this.publishDate = new Date();
        this.text = text;
    }
}
