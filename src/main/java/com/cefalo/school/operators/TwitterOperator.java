package com.cefalo.school.operators;

import com.cefalo.school.model.FeedItem;
import com.thirdparty.api.InstagramApi;
import com.thirdparty.api.TwitterApi;
import org.json.JSONArray;
import org.json.JSONObject;

public class TwitterOperator implements FeedOperator {
    public JSONObject jsonObject = null;

    public boolean postUpdate(JSONObject item) {
        if (jsonObject != null){
            JSONArray array = jsonObject.getJSONArray("data");
            array.put(item);
            // post to twitter api
            return true;
        }
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
