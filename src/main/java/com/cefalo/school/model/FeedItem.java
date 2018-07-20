package com.cefalo.school.model;

import java.rmi.server.UID;
import java.util.Date;
import java.util.List;

public class FeedItem {
    public String identifier;
    public Date publishedDate;
    public List<Content> contents;
    public UID applicationIdentifier;
}
