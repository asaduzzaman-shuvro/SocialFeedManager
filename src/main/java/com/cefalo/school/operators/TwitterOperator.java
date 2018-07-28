package com.cefalo.school.operators;

import com.cefalo.school.model.FeedItem;
import com.thirdparty.api.InstagramApi;
import com.thirdparty.api.TwitterApi;
import org.json.JSONArray;
import org.json.JSONObject;

public class TwitterOperator implements FeedOperator {
    public JSONObject jsonObject = null;

    public boolean postItem(JSONObject item) {
        if (jsonObject != null){
            JSONArray array = jsonObject.getJSONArray("data");
            for (Object object : array) {
                JSONObject jsonItem = (JSONObject) object;
                if(jsonItem.getString("id_str").equals(item.getString("id_str"))){
                    jsonItem = item;
                    return true;
                }
            }
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
