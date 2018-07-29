package com.cefalo.school.operators;

import com.thirdparty.api.InstagramApi;
import org.json.JSONArray;
import org.json.JSONObject;

public class InstagramOperator implements FeedOperator {
    public JSONObject jsonObject = null;

    public boolean postItem(JSONObject item) {

        if (jsonObject != null){
            JSONArray array = jsonObject.getJSONArray("data");
            int i=0;
            for (Object object : array) {
                JSONObject jsonItem = (JSONObject) object;
                if(jsonItem.getString("id").equals(item.getString("id"))){
                    array.put(i, item);
                    return true;
                }
                i++;
            }
            array.put(item);
            // post to instagram api
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
