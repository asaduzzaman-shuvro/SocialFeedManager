package com.cefalo.school.processors;

import com.cefalo.school.model.FeedItem;
import com.cefalo.school.model.SFMAction;
import java.util.List;
import java.util.UUID;

public interface FeedProcessor {
    public List<FeedItem> getFeedItems(String authToken);
    public boolean addAction(FeedItem feedItem, SFMAction action, String authToken);
    public boolean postUpdate(FeedItem item, String authToken);
    public UUID getApplicationIdentifier();
}
