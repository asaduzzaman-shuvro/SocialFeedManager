package com.cefalo.school.processors;

import com.cefalo.school.mapper.InstagramFeedMapper;
import com.cefalo.school.model.FeedItem;
import com.cefalo.school.operators.InstagramOperator;
import java.util.List;

public class InstagramFeedProcessor implements FeedProcessor{
    InstagramOperator feedOperator = new InstagramOperator();
    InstagramFeedMapper feedMapper = new InstagramFeedMapper();


    public List<FeedItem> getFeedItems() {
        if (feedOperator.getFeed()) {
            return feedMapper.getProcessedFeedItems(feedOperator.jsonObject);
        }
        return null;
    }
}
