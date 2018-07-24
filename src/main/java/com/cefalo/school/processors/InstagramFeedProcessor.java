package com.cefalo.school.processors;

import com.cefalo.school.mapper.InstagramFeedMapper;
import com.cefalo.school.model.FeedItem;
import com.cefalo.school.operators.InstagramOperator;
import java.util.List;
import java.util.UUID;

public class InstagramFeedProcessor implements FeedProcessor{

    @Override
    public List<FeedItem> getFeedItems(UUID applicationIdentifier) {
        return null;
    }
}
