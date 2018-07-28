package com.cefalo.school.operators;

import com.cefalo.school.model.FeedItem;
import com.thirdparty.api.FacebookApi;
import com.thirdparty.api.InstagramApi;
import org.json.JSONArray;
import org.json.JSONObject;

public class InstagramOperator implements FeedOperator {
    public JSONObject jsonObject = null;

    public boolean postItem(JSONObject item) {

        if (jsonObject != null){
            JSONArray array = jsonObject.getJSONArray("data");
            array.put(item);
            // post to innstagram api
            return true;
        }
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
