package com.cefalo.school.mapper;

import com.cefalo.school.model.FeedItem;
import org.json.JSONObject;

import java.util.List;

public interface FeedMapper {
    List<FeedItem> getProcessedFeedItems(JSONObject jsonObject);
}
