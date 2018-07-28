package com.cefalo.school;

import com.cefalo.school.application.SocialFeedManager;
import com.cefalo.school.model.*;
import com.cefalo.school.processors.FacebookFeedProcessor;
import com.cefalo.school.processors.InstagramFeedProcessor;

import com.cefalo.school.services.OutputGeneratorService;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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

  public static void facebookPostTest(){
    SocialFeedManager manager = new SocialFeedManager();
    manager.getAllFeedItems();

    FacebookFeedItem itemToPost = new FacebookFeedItem();
    itemToPost.contents.add(new Content(ContentType.TEXT, "", "My first status from SFM"));

    List<UUID> identifiers = new ArrayList<>();
    identifiers.add(manager.getApplicationIdentifiers().get(1));

    List<FeedItem> items = new ArrayList<>();
    if(manager.postItem(itemToPost, identifiers)){
      items = manager.getAllFeedItems();
    }
  }

  public static void facebookTest(){
    UUID uniqueAppId = UUID.randomUUID();
    FacebookFeedProcessor facebookFeedProcessor = new FacebookFeedProcessor(uniqueAppId);
    List<FeedItem> feedItems = facebookFeedProcessor.getFeedItems("");

    feedItems.forEach(item->{
      FacebookFeedItem newItem = (FacebookFeedItem)item;
      newItem.contents.forEach(content -> {
      System.out.println("======================================");
      System.out.println(content.contentType + "-" + content.value);
    });
      newItem.reactions.forEach((k,v)-> System.out.println(k + ":" + v ));
      System.out.println("comments: " + newItem.comments.size());
      System.out.println("application Identifier: " + newItem.applicationIdentifier);
      System.out.println("======================================");
    });
  }

  public static void testFBUpdate(){
    UUID uniqueAppId = UUID.randomUUID();
    FacebookFeedProcessor facebookFeedProcessor = new FacebookFeedProcessor(uniqueAppId);
    List<FeedItem> feedItems = facebookFeedProcessor.getFeedItems("");
    System.out.println(String.format("%s items found in facebook feed", feedItems.size()));
    System.out.println("now like 3rd item from the feed list");

    FacebookFeedItem newItem = (FacebookFeedItem)facebookFeedProcessor.feedItems.get(2);
    newItem.reactions.forEach((k,v)->{
      if(k == "like"){
        System.out.println("initial like count for this post");
        System.out.println("Like: " + v);
      }
    });
    FacebookFeedItem targetFeedItem = (FacebookFeedItem)feedItems.get(2);

    facebookFeedProcessor.addAction(targetFeedItem, new SFMAction(FBActionType.LIKE), "");
    newItem.reactions.forEach((k,v)->{
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

    FacebookFeedItem newItem = (FacebookFeedItem)feedItems.get(2);
    newItem.comments.forEach(comment->{
      System.out.println("previous comment: " + comment.contents.get(0).value);
    });

    String targetCommentId = newItem.comments.get(0).identifier;

    facebookFeedProcessor.updateComment(uniqueAppId, targetCommentId, "update test comment....");
    FacebookFeedItem target = (FacebookFeedItem)facebookFeedProcessor.feedItems.get(2);
    target.comments.forEach(comment->{
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

    List<FeedItem> items = new ArrayList<>();
    if(manager.postItem(itemToPost, identifiers)){
      items = manager.getAllFeedItems();
    }

    FeedItem itemToEdit = items.get(0);
//    items.
    if(manager.postItem(itemToEdit, identifiers)){
      manager.getAllFeedItems();
    }
    File dir = new File("output");
    dir.mkdirs();
    File file = new File(dir, "output.txt");

    OutputGeneratorService.outputToFile(items, file);
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
