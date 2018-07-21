package com.cefalo.school.operators;

import com.cefalo.school.model.FeedItem;
import com.thirdparty.api.FacebookApi;
import com.thirdparty.api.InstagramApi;
import org.json.JSONObject;

public class InstagramOperator implements FeedOperator {
    public JSONObject jsonObject = null;

    public boolean postUpdate(FeedItem item) {
        return false;
    }

    public boolean getFeed() {

        jsonObject = InstagramApi.getFeeds();
        return jsonObject != null;

    }
}
