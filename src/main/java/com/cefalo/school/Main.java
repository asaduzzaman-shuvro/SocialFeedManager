package com.cefalo.school;

import com.thirdparty.api.FacebookApi;
import com.thirdparty.api.InstagramApi;
import com.thirdparty.api.TwitterApi;

/**
 * Created by atiqul on 7/17/2018.
 */
public class Main {

  public static void main(String[] args) {
    System.out.println(TwitterApi.getFeeds());
  }
}
