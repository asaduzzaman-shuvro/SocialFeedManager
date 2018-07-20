package com.cefalo.school.operators;

import com.cefalo.school.model.FeedItem;

public class InstagramOperator implements FeedOperator {
    public boolean postUpdate(FeedItem item) {
        return false;
    }

    public boolean getFeed() {
        return false;
    }
}
