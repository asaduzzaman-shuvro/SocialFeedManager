package com.cefalo.school.processors;

import com.cefalo.school.mapper.InstagramFeedMapper;
import com.cefalo.school.model.FeedItem;
import com.cefalo.school.operators.InstagramOperator;
import java.util.List;
import java.util.UUID;

public class InstagramFeedProcessor implements FeedProcessor{
    InstagramOperator feedOperator = new InstagramOperator();
    InstagramFeedMapper feedMapper = new InstagramFeedMapper();


    @Override
    public List<FeedItem> getFeedItems(UUID applicationIdentifier) {
        return null;
    }

    @Override
    public void updateFeedItem(UUID applicationIdentifier, FeedItem feedItem, Enum action) {

    }
}
