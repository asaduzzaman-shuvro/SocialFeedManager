package com.cefalo.school.operators;

import com.cefalo.school.model.FeedItem;
import com.thirdparty.api.FacebookApi;
import com.thirdparty.api.InstagramApi;
import org.json.JSONObject;

public class InstagramOperator implements FeedOperator {
    public JSONObject jsonObject = null;

    public boolean postUpdate(JSONObject item) {
        return false;
    }

    @Override
    public boolean getFeed(String authToken) {
        if(jsonObject == null) {
            jsonObject = InstagramApi.getFeeds(authToken);
        }
        return jsonObject != null;
    }
}
