package com.cefalo.school.model;

import java.util.UUID;

public class Comment{
    public String identifier = "";
    public String text = "";

    public Comment(String identifier, String text) {
        this.identifier = identifier;
        this.text = text;
    }

    public Comment(String text) {
        this.identifier = UUID.randomUUID().toString();
        this.text = text;
    }
}
