package com.cefalo.school.processors;

import com.cefalo.school.mapper.InstagramFeedMapper;
import com.cefalo.school.mapper.TwitterFeedMapper;
import com.cefalo.school.model.FeedItem;
import com.cefalo.school.operators.InstagramOperator;
import com.cefalo.school.operators.TwitterOperator;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TwitterFeedProcessor implements FeedProcessor{
    TwitterOperator feedOperator = new TwitterOperator();
    TwitterFeedMapper feedMapper = new TwitterFeedMapper();

    private UUID applicationIdentifier;
    public List<FeedItem> feedItems = new ArrayList<>();

    public TwitterFeedProcessor(UUID appIdentifier){
        this.applicationIdentifier = appIdentifier;
    }

    @Override
    public List<FeedItem> getFeedItems() {
        return null;
    }

    @Override
    public void updateFeedItem(FeedItem feedItem, Enum action) {

    }

    @Override
    public UUID getApplicationIdentifier() {
        return applicationIdentifier;
    }
}
