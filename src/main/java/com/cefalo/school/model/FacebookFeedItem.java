package com.cefalo.school.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FacebookFeedItem extends FeedItem {
    
    public Map<String, Integer> reactions = new HashMap<String, Integer>();
    public List<FeedItem> comments = new ArrayList<FeedItem>();

    public FacebookFeedItem() {
        super();
    }
}
