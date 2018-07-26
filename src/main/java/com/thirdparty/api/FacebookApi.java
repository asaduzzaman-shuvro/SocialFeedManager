package com.thirdparty.api;

import java.io.*;
import org.json.*;

/**
 * Created by atiqul on 7/17/2018.
 */
public class FacebookApi {

  public static JSONObject getFeeds(String authToken) {
    String filename = "Facebook.json";
    JSONObject facebookFeeds = null;
    try {
      facebookFeeds = JsonParser.parseJSONFile(filename);
    } catch (IOException e) {
      e.printStackTrace();
    }

    return facebookFeeds;

  }

  public static boolean postItem(String authToken, Object postObject){
    return true;
  }


}
