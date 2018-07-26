package com.cefalo.school;

import com.cefalo.school.application.AccountManager;
import com.cefalo.school.application.Application;
import com.cefalo.school.application.ApplicationType;
import com.cefalo.school.application.SocialFeedManager;
import com.cefalo.school.model.Content;
import com.cefalo.school.model.ContentType;
import com.cefalo.school.model.FeedItem;
import com.cefalo.school.processors.FBActionType;
import com.cefalo.school.processors.FacebookFeedProcessor;
import com.cefalo.school.processors.InstagramFeedProcessor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by atiqul on 7/17/2018.
 */
public class Main {

  public static void main(String[] args) {

//    facebookTest();
//    testFBUpdate();
//    testFbCommentUpdate();
    twitterTest();
  }

  public static void facebookTest(){
    UUID uniqueAppId = UUID.randomUUID();
    FacebookFeedProcessor facebookFeedProcessor = new FacebookFeedProcessor(uniqueAppId);
    List<FeedItem> feedItems = facebookFeedProcessor.getFeedItems("");

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
    FacebookFeedProcessor facebookFeedProcessor = new FacebookFeedProcessor(uniqueAppId);
    List<FeedItem> feedItems = facebookFeedProcessor.getFeedItems("");
    System.out.println(String.format("%s items found in facebook feed", feedItems.size()));
    System.out.println("now like 3rd item from the feed list");

    facebookFeedProcessor.feedItems.get(2).reactions.forEach((k,v)->{
      if(k == "like"){
        System.out.println("initial like count for this post");
        System.out.println("Like: " + v);
      }
    });
    FeedItem targetFeedItem = feedItems.get(2);

    facebookFeedProcessor.updateFeedItem(targetFeedItem, FBActionType.LIKE);
    facebookFeedProcessor.feedItems.get(2).reactions.forEach((k,v)->{
      if(k == "like"){
        System.out.println("after given like");
        System.out.println("Like: " + v);
      }
    });

  }

  public static void testFbCommentUpdate(){
    UUID uniqueAppId = UUID.randomUUID();
    FacebookFeedProcessor facebookFeedProcessor = new FacebookFeedProcessor(uniqueAppId);
    List<FeedItem> feedItems = facebookFeedProcessor.getFeedItems("");
    System.out.println(String.format("%s items found in facebook feed", feedItems.size()));
    System.out.println("now update comment of 3rd item from the feed list");

    feedItems.get(2).comments.forEach(comment->{
      System.out.println("previous comment: " + comment.contents.get(0).value);
    });
    String targetCommentId = feedItems.get(2).comments.get(0).identifier;

    facebookFeedProcessor.updateComment(uniqueAppId, targetCommentId, "update test comment....");
    facebookFeedProcessor.feedItems.get(2).comments.forEach(comment->{
      System.out.println("current comment: " + comment.contents.get(0).value);
    });


  }

  public static void twitterTest(){
    SocialFeedManager manager = new SocialFeedManager();
    manager.getAllFeedItems();


    // test post update to twitter
    FeedItem itemToPost = new FeedItem();
    itemToPost.contents.add(new Content(ContentType.TEXT, "", "My first status from SFM"));

    List<UUID> identifiers = new ArrayList<>();
    identifiers.add(manager.getApplicationIdentifiers().get(1));

    if(manager.postItem(itemToPost, identifiers)){
      manager.getAllFeedItems();
    }
  }

  public static void instagramTest(){
    UUID uniqueAppId = UUID.randomUUID();
    InstagramFeedProcessor feedProcessor = new InstagramFeedProcessor(uniqueAppId);
    List<FeedItem> feedItems = feedProcessor.getFeedItems("");

    System.out.println("\n\n\nInstagram feeds");
    for (FeedItem item: feedItems) {
      System.out.println(item.contents.get(0).value);
    }


  }
}
