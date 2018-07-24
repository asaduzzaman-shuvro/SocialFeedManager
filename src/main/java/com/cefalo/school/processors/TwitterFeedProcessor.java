package com.cefalo.school.processors;

import com.cefalo.school.mapper.TwitterFeedMapper;
import com.cefalo.school.model.FeedItem;
import com.cefalo.school.operators.TwitterOperator;
import java.util.List;
import java.util.UUID;

public class TwitterFeedProcessor implements FeedProcessor{

    @Override
    public List<FeedItem> getFeedItems(UUID applicationIdentifier) {
        return null;
    }

    @Override
    public void updateFeedItem(UUID applicationIdentifier, FeedItem feedItem, Enum action) {

    }


}
