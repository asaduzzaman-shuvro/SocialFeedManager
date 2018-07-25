package com.cefalo.school;

import com.cefalo.school.model.FeedItem;
import com.cefalo.school.processors.FBActionType;
import com.cefalo.school.processors.FacebookFeedProcessor;
import com.cefalo.school.processors.InstagramFeedProcessor;
import java.util.List;
import java.util.UUID;

/**
 * Created by atiqul on 7/17/2018.
 */
public class Main {

  public static void main(String[] args) {

//    facebookTest();
//    testFBUpdate();
    testFbCommentUpdate();

  }

  public static void facebookTest(){
    FacebookFeedProcessor facebookFeedProcessor = new FacebookFeedProcessor();
    List<FeedItem> feedItems = facebookFeedProcessor.getFeedItems(UUID.randomUUID());

    feedItems.forEach(item->{item.contents.forEach(content -> {
      System.out.println("======================================");
      System.out.println(content.contentType + "-" + content.value);
    });
      item.reactions.forEach((k,v)-> System.out.println(k + ":" + v ));
      System.out.println("comments: " + item.comments.size());
      System.out.println("application Identifier: " + item.applicationIdentifier);
      System.out.println("======================================");
    });
  }

  public static void testFBUpdate(){
    UUID uniqueAppId = UUID.randomUUID();
    FacebookFeedProcessor facebookFeedProcessor = new FacebookFeedProcessor();
    List<FeedItem> feedItems = facebookFeedProcessor.getFeedItems(uniqueAppId);
    System.out.println(String.format("%s items found in facebook feed", feedItems.size()));
    System.out.println("now like 3rd item from the feed list");

    facebookFeedProcessor.fbFeedItems.get(2).reactions.forEach((k,v)->{
      if(k == "like"){
        System.out.println("initial like count for this post");
        System.out.println("Like: " + v);
      }
    });
    FeedItem targetFeedItem = feedItems.get(2);

    facebookFeedProcessor.updateFeedItem(uniqueAppId, targetFeedItem, FBActionType.LIKE);
    facebookFeedProcessor.fbFeedItems.get(2).reactions.forEach((k,v)->{
      if(k == "like"){
        System.out.println("after given like");
        System.out.println("Like: " + v);
      }
    });

  }

  public static void testFbCommentUpdate(){
    UUID uniqueAppId = UUID.randomUUID();
    FacebookFeedProcessor facebookFeedProcessor = new FacebookFeedProcessor();
    List<FeedItem> feedItems = facebookFeedProcessor.getFeedItems(uniqueAppId);
    System.out.println(String.format("%s items found in facebook feed", feedItems.size()));
    System.out.println("now update comment of 3rd item from the feed list");

    feedItems.get(2).comments.forEach(comment->{
      System.out.println("previous comment: " + comment.contents.get(0).value);
    });
    String targetCommentId = feedItems.get(2).comments.get(0).identifier;

    facebookFeedProcessor.updateComment(uniqueAppId, targetCommentId, "update test comment....");
    facebookFeedProcessor.fbFeedItems.get(2).comments.forEach(comment->{
      System.out.println("current comment: " + comment.contents.get(0).value);
    });


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
