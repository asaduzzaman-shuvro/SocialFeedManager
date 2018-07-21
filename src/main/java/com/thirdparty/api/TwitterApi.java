package com.thirdparty.api;

import org.json.JSONObject;

import java.io.IOException;

public class TwitterApi {
    public static JSONObject getFeeds() {
        String filename = "Twitter.json";
        JSONObject tweeterFeeds = null;
        try {
            tweeterFeeds = JsonParser.parseJSONFile(filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tweeterFeeds;
    }
}
