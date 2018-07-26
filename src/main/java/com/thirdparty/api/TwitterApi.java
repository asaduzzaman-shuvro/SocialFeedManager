package com.thirdparty.api;

import com.cefalo.school.model.FeedItem;
import org.json.JSONObject;

import java.io.IOException;

public class TwitterApi {
    public static JSONObject getFeeds(String authToken) {
        String filename = "Twitter.json";
        JSONObject tweeterFeeds = null;
        try {
            tweeterFeeds = JsonParser.parseJSONFile(filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tweeterFeeds;
    }

    public static boolean postItem(String authToken, Object postObject){
        // post to twitter with twitter api
        return true;
    }
}
