package com.cefalo.school.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class FeedItem {
    public String identifier = "";
    public String userID = "";
    public Date publishedDate;
    public UUID applicationIdentifier;
    public List<Content> contents = new ArrayList<Content>();

    // for facebook only
    public Date lastModifiedDate;
    public Map<String, Integer> reactions = new HashMap<String, Integer>();
    public List<FeedItem> comments = new ArrayList<FeedItem>();
    //TODO: move these to some other place or
}
