package com.cefalo.school.operators;

import com.cefalo.school.model.FeedItem;
import com.thirdparty.api.InstagramApi;
import com.thirdparty.api.TwitterApi;
import org.json.JSONObject;

public class TwitterOperator implements FeedOperator {
    public JSONObject jsonObject = null;

    public boolean postUpdate(FeedItem item) {
        return false;
    }

    @Override
    public boolean getFeed(String authToken) {
        if(jsonObject == null) {
            jsonObject = TwitterApi.getFeeds(authToken);
        }
        return jsonObject != null;
    }
}
