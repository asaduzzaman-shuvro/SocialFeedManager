package com.cefalo.school.operators;

import org.json.JSONObject;

public interface FeedOperator {
    boolean postItem(JSONObject item);
    boolean getFeed(String authToken);
}
