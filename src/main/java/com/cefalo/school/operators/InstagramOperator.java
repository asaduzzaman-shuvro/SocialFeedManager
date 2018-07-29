package com.cefalo.school.operators;

import com.thirdparty.api.InstagramApi;
import org.json.JSONArray;
import org.json.JSONObject;

public class InstagramOperator implements FeedOperator {
    public JSONObject jsonObject = null;

    public boolean postItem(JSONObject item) {


        if (jsonObject != null){

            if (jsonObject.has("graphql")){
                JSONObject graphphql = jsonObject.getJSONObject("graphql");
                if (graphphql.has("user")){
                    JSONObject userData = graphphql.getJSONObject("user");
                    if (userData.has("edge_owner_to_timeline_media")){
                        JSONObject timelineData = userData.getJSONObject("edge_owner_to_timeline_media");
                        if (timelineData.has("edges")){
                            JSONArray allNodes = timelineData.getJSONArray("edges");
                            int i=0;
                            for (Object object : allNodes) {
                                JSONObject jsonItem = ((JSONObject) object).getJSONObject("node");

                                if (jsonItem.getString("id").equals(item.getJSONObject("node").getString("id"))){
                                    allNodes.put(i, item);
                                }
                                i++;
                            }
                            allNodes.put(item);
                        }
                    }
                }
            }
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
