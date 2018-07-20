package com.cefalo.school.operators;

import com.cefalo.school.model.FeedItem;

public interface FeedOperator {
    boolean postUpdate(FeedItem item);
    boolean getFeed();
}
