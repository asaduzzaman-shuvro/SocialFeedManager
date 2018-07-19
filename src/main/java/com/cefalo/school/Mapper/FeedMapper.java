package com.cefalo.school.Mapper;

import com.cefalo.school.Model.FeedItem;

import java.util.List;

public interface FeedMapper {
    List<FeedItem> getProcessedFeedItems();
}
