package com.cefalo.school.services;

import com.cefalo.school.Mapper.FeedMapper;

public class SocialFeedOperator {
    private FeedMapper feedMapper;
    private FeedOperationProcessor feedOperationProcessor;


    public SocialFeedOperator(FeedMapper feedMapper, FeedOperationProcessor feedOperationProcessor) {
        this.feedMapper = feedMapper;
        this.feedOperationProcessor = feedOperationProcessor;
    }

    public FeedMapper getFeedMapper() {
        return feedMapper;
    }

    public FeedOperationProcessor getFeedOperationProcessor() {
        return feedOperationProcessor;
    }
}
