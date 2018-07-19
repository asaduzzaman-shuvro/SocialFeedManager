package com.cefalo.school.services;

import com.cefalo.school.Model.FeedItem;

public class FacebookFeedProcessor implements FeedOperationProcessor {
    public boolean postUpdate(FeedItem item) {
        return false;
    }
}
