package com.cefalo.school;

import com.thirdparty.api.FacebookApi;
import org.json.JSONObject;

/**
 * Created by atiqul on 7/19/2018.
 */
public class FacebookFeeds {

  public static void getFeeds(String userName, String authToken){


    JSONObject fbFeed = FacebookApi.getFeeds();

  }

}
