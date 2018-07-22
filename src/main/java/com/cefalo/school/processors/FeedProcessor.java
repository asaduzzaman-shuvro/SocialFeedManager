package com.cefalo.school.processors;

import com.cefalo.school.mapper.FeedMapper;
import com.cefalo.school.model.FeedItem;
import com.cefalo.school.operators.FeedOperator;
import java.util.ArrayList;
import java.util.List;

public interface FeedProcessor {
    public List<FeedItem> getFeedItems();
}
