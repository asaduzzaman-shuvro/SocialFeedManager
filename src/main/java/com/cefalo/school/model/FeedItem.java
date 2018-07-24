package com.cefalo.school.model;

import java.rmi.server.UID;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FeedItem {
    public String identifier;
    public Date publishedDate;
    public Date lastModifiedDate;
    public List<Content> contents = new ArrayList<Content>();
    public Map<String, Integer> reactions = new HashMap<String, Integer>();
    public List<Content> comments = new ArrayList<Content>();
    public UID applicationIdentifier;
}
