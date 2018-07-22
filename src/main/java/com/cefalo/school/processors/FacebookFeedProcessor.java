package com.cefalo.school.processors;

import com.cefalo.school.mapper.FacebookFeedMapper;
import com.cefalo.school.mapper.FeedMapper;
import com.cefalo.school.model.FeedItem;
import com.cefalo.school.operators.FacebookOperator;
import com.cefalo.school.operators.FeedOperator;
import java.util.List;

public class FacebookFeedProcessor implements FeedProcessor {

    FacebookOperator feedOperator = new FacebookOperator();
    FacebookFeedMapper feedMapper = new FacebookFeedMapper();

    public List<FeedItem> getFeedItems(){
        if(feedOperator.getFeed()) {
            return feedMapper.getProcessedFeedItems(feedOperator.jsonObject);
        }
        return null;
    }
}
