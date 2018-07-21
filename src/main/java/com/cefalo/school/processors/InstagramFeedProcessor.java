package com.cefalo.school.processors;

import com.cefalo.school.mapper.InstagramMapper;
import com.cefalo.school.operators.InstagramOperator;

public class InstagramFeedProcessor extends FeedProcessor{
    public InstagramFeedProcessor() {
        this.feedOperator = new InstagramOperator();
        this.feedMapper = new InstagramMapper();
    }
}
