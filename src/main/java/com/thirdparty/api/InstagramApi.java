package com.thirdparty.api;

import org.json.JSONObject;

import java.io.IOException;

public class InstagramApi {
    public static JSONObject getFeeds() {
        String filename = "Instagram.json";
        JSONObject instagramFeeds = null;
        try {
            instagramFeeds = JsonParser.parseJSONFile(filename);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return instagramFeeds;

    }
}
