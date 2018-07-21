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

    public boolean getFeed() {
        jsonObject = TwitterApi.getFeeds();
        return jsonObject != null;
    }
}
