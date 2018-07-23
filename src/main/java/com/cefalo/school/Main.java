package com.cefalo.school;

import com.cefalo.school.model.FeedItem;
import com.cefalo.school.processors.FacebookFeedProcessor;
import java.util.List;

/**
 * Created by atiqul on 7/17/2018.
 */
public class Main {

  public static void main(String[] args) {

    facebookTest();

  }

  public static void facebookTest(){
    FacebookFeedProcessor facebookFeedProcessor = new FacebookFeedProcessor();
    List<FeedItem> feedItems = facebookFeedProcessor.getFeedItems();

    feedItems.forEach(item->{item.contents.forEach(content -> {
      System.out.println("======================================");
      System.out.println(content.contentType + "-" + content.value);
    });
      item.reactions.forEach((k,v)-> System.out.println(k + ":" + v ));
      System.out.println("comments: " + item.comments.size());
      System.out.println("======================================");
    });
  }

  public static void twitterTest(){

  }

  public static void instagramTest(){

  }
}
