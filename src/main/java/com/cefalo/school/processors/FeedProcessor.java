package com.cefalo.school.processors;

import com.cefalo.school.model.FeedItem;
import java.util.List;
import java.util.UUID;

public interface FeedProcessor {
    public List<FeedItem> getFeedItems(String authToken);
    public void updateFeedItem(FeedItem feedItem, Enum action);
    public boolean postUpdate(FeedItem item, String authToken);
    public UUID getApplicationIdentifier();
}
