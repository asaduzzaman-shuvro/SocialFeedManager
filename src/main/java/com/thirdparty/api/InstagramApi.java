package com.thirdparty.api;

import com.cefalo.school.services.SFMUtils;
import org.json.JSONObject;

import java.io.IOException;

public class InstagramApi {
    public static JSONObject getFeeds(String authToken) {
        String filename = "Instagram.json";
        JSONObject instagramFeeds = null;
        try {
            instagramFeeds = SFMUtils.parseJSONFile(filename);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return instagramFeeds;

    }

    public static boolean postItem(String authToken, Object postObject){
        return true;
    }
}
