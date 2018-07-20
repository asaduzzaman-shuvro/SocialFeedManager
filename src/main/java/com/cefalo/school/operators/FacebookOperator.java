package com.cefalo.school.operators;

import com.cefalo.school.model.FeedItem;

public class FacebookOperator implements FeedOperator {
    public boolean postUpdate(FeedItem item) {
        return false;
    }

    public boolean getFeed() {
        return false;
    }
}
