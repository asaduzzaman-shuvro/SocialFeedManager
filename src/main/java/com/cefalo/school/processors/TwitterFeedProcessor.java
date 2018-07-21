package com.cefalo.school.processors;

import com.cefalo.school.mapper.TwitterFeedMapper;
import com.cefalo.school.operators.TwitterOperator;

public class TwitterFeedProcessor extends FeedProcessor{
    public TwitterFeedProcessor() {
        this.feedOperator = new TwitterOperator();
        this.feedMapper = new TwitterFeedMapper();
    }
}
