package com.cefalo.school.processors;

import com.cefalo.school.model.FeedItem;
import java.util.List;
import java.util.UUID;

public interface FeedProcessor {
    public List<FeedItem> getFeedItems();
    public void updateFeedItem(FeedItem feedItem, Enum action);
    public UUID getApplicationIdentifier();
}
