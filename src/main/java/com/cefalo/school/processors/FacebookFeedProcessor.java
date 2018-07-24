package com.cefalo.school.processors;

import com.cefalo.school.mapper.FacebookFeedMapper;
import com.cefalo.school.mapper.FeedMapper;
import com.cefalo.school.model.FeedItem;
import com.cefalo.school.operators.FacebookOperator;
import com.cefalo.school.operators.FeedOperator;
import java.util.List;
import java.util.UUID;

public class FacebookFeedProcessor implements FeedProcessor {

    FacebookOperator feedOperator = new FacebookOperator();
    FacebookFeedMapper feedMapper = new FacebookFeedMapper();

    public List<FeedItem> getFeedItems(UUID applicationIdentifier){
        if(feedOperator.getFeed()) {
            return feedMapper.getProcessedFeedItems(applicationIdentifier, feedOperator.jsonObject);
        }
        return null;
    }
}
