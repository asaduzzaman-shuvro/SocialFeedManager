package com.cefalo.school;

import com.cefalo.school.model.FeedItem;
import com.cefalo.school.operators.FacebookOperator;
import com.cefalo.school.processors.FacebookFeedProcessor;
import com.cefalo.school.processors.InstagramFeedProcessor;
import com.thirdparty.api.FacebookApi;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by atiqul on 7/17/2018.
 */
public class Main {

  public static void main(String[] args) {

    facebookTest();
    instagramTest();
  }

  public static void facebookTest(){
    FacebookFeedProcessor facebookFeedProcessor = new FacebookFeedProcessor();
    List<FeedItem> feedItems = facebookFeedProcessor.getFeedItems();

    System.out.println("FacebookFeeds");
    for(FeedItem item : feedItems){
      String message = item.contents.get(0).value;
      System.out.println(message);
    }
  }

  public static void twitterTest(){

  }

  public static void instagramTest(){
    InstagramFeedProcessor feedProcessor = new InstagramFeedProcessor();
    List<FeedItem> feedItems = feedProcessor.getFeedItems();

    System.out.println("\n\n\nInstagram feeds");
    for (FeedItem item: feedItems) {
      System.out.println(item.contents.get(0).value);
    }


  }
}
