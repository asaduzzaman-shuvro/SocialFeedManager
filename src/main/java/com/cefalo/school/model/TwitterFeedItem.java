package com.cefalo.school.model;

import java.util.ArrayList;
import java.util.List;

public class TwitterFeedItem extends FeedItem {
    public int retweetCount = 0;
    public int favoriteCount = 0;
    public List<Comment> comments = new ArrayList<>();

    public TwitterFeedItem() {
        super();
    }
}

