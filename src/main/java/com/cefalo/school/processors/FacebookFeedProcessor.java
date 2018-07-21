package com.cefalo.school.processors;

import com.cefalo.school.mapper.FacebookFeedMapper;
import com.cefalo.school.operators.FacebookOperator;

public class FacebookFeedProcessor extends FeedProcessor {

    public FacebookFeedProcessor() {
        this.feedOperator = new FacebookOperator();
        this.feedMapper = new FacebookFeedMapper();
    }
}
