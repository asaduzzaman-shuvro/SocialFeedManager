package com.cefalo.school.mapper;

import com.cefalo.school.model.FeedItem;
import java.util.UUID;
import org.json.JSONObject;

import java.util.List;

public interface FeedMapper {
    List<FeedItem> getProcessedFeedItems(UUID applicationIdentifier, JSONObject jsonObject);
    JSONObject mapFeedItemToJSON(FeedItem item);
}
