package com.cefalo.school.mapper;

import com.cefalo.school.model.FeedItem;

import java.util.List;

public interface FeedMapper {
    List<FeedItem> getProcessedFeedItems();
}
