package com.cefalo.school.operators;

import com.cefalo.school.model.FeedItem;
import org.json.JSONObject;

public interface FeedOperator {
    boolean postUpdate(JSONObject item);
    boolean getFeed(String authToken);
}
