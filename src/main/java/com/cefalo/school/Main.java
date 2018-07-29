package com.cefalo.school;

import com.cefalo.school.application.SocialFeedManager;
import com.cefalo.school.model.*;
import com.cefalo.school.processors.InstagramFeedProcessor;

import com.cefalo.school.services.SFMUtils;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by atiqul on 7/17/2018.
 */
public class Main {

  public static void main(String[] args) {

//    facebookTest();
//    twitterTest();
    instagramTest();
  }


  public static void facebookTest(){
    SocialFeedManager manager = new SocialFeedManager();
    manager.getAllFeedItems();

    // test post to Facebook

    FeedItem itemToPost = new FacebookFeedItem();
    itemToPost.contents.add(new Content(ContentType.TEXT, "", "This is test post to Facebook from SFM"));

    List<UUID> identifiers = new ArrayList<>();
    identifiers.add(manager.getApplicationIdentifiers().get(0));

    List<FeedItem> items = new ArrayList<>();
    if(manager.postItem(itemToPost, identifiers)){
      items = manager.getAllFeedItems();
    }

    File dir = new File("output");
    dir.mkdirs();
    File file1 = new File(dir, "post_to_facebook.txt");

    SFMUtils.outputToFile(items, file1);

    /* Edit given post to Facebook from SFM*/

    FeedItem itemToEdit = items.get(0);
    for (Content content : itemToEdit.contents) {
      if(content.contentType == ContentType.TEXT){
        content.description = "Edit first test post to Facebook from SFM";
      }
    }

    if(manager.editFeedItem(itemToEdit)){
      items = manager.getAllFeedItems();
    }

    File file = new File(dir, "edit_post_to_facebook.txt");

    SFMUtils.outputToFile(items, file);

    // add like to this post

    if(manager.addAction(itemToEdit, new SFMAction(FBActionType.LIKE))){
      items = manager.getAllFeedItems();
    }


    File file2= new File(dir, "like_facebook_post.txt");

    SFMUtils.outputToFile(items, file2);

    // add comment to this post

    if(manager.addAction(itemToEdit, new SFMAction(FBActionType.COMMENT, "First comment on the test post"))){
      items = manager.getAllFeedItems();
    }

    File file3= new File(dir, "add_comment_to_facebook_post.txt");

    SFMUtils.outputToFile(items, file3);

    /* edit comment to this post */

    FacebookFeedItem targetItem = (FacebookFeedItem)items.get(0);
    targetItem.comments.forEach(comment->{
      System.out.println("previous comment: " + comment.contents.get(0).description);
    });

    String targetCommentId = targetItem.comments.get(0).identifier;

    if(manager.editComment(targetItem, targetCommentId, "Comment edited..")){
      items = manager.getAllFeedItems();
    }

    File file4= new File(dir, "edit_comment_to_facebook_post.txt");

    SFMUtils.outputToFile(items, file4);


  }

  public static void twitterTest(){
    SocialFeedManager manager = new SocialFeedManager();
    manager.getAllFeedItems();


    // test post update to twitter

    FeedItem itemToPost = new TwitterFeedItem();
//    FeedItem itemToPost = (FeedItem) fbItem;
    itemToPost.contents.add(new Content(ContentType.TEXT, "", "My first status from SFM"));

    List<UUID> identifiers = new ArrayList<>();
    identifiers.add(manager.getApplicationIdentifiers().get(1));

    List<FeedItem> items = new ArrayList<>();
    if(manager.postItem(itemToPost, identifiers)){
      items = manager.getAllFeedItems();
    }

    File dir = new File("output");
    dir.mkdirs();
    File file1 = new File(dir, "tweetoutput0.txt");

    SFMUtils.outputToFile(items, file1);

    FeedItem itemToEdit = items.get(0);
    for (Content content : itemToEdit.contents) {
      if(content.contentType == ContentType.TEXT){
        content.description = "this the changed text for this post";
      }
    }

    if(manager.editFeedItem(itemToEdit)){
      items = manager.getAllFeedItems();
    }

    File file = new File(dir, "tweetoutput1.txt");

    SFMUtils.outputToFile(items, file);

    // add favorite

    if(manager.addAction(itemToEdit, new SFMAction(TwitterActionType.FAVORITE))){
      items = manager.getAllFeedItems();
    }

    File file2= new File(dir, "tweetoutput2.txt");

    SFMUtils.outputToFile(items, file2);

    // add comment

    if(manager.addAction(itemToEdit, new SFMAction(TwitterActionType.COMMENT, "aloha comment"))){
      items = manager.getAllFeedItems();
    }

    File file3= new File(dir, "tweetoutput3.txt");

    SFMUtils.outputToFile(items, file3);
  }

  public static void instagramTest(){

    SocialFeedManager manager = new SocialFeedManager();
    manager.getAllFeedItems();

    List<UUID> identifiers = new ArrayList<>();
    identifiers.add(manager.getApplicationIdentifiers().get(2));

    FeedItem itemToPost = new FeedItem();
    itemToPost.contents.add(new Content(ContentType.TEXT, "", "Insatagram Status"));

    List<FeedItem> items = new ArrayList<>();
    if(manager.postItem(itemToPost, identifiers)){
      items = manager.getAllFeedItems();
    }

    File dir = new File("output");
    dir.mkdirs();
    File file1 = new File(dir, "Instagram.txt");

    SFMUtils.outputToFile(items, file1);


    System.out.println(items.get(0).contents.get(0).description);


    FeedItem itemToEdit = items.get(0);
    for (Content content : itemToEdit.contents) {
      if(content.contentType == ContentType.TEXT){
        content.description = "eited instagram status";
      }
    }

    if(manager.editFeedItem(itemToEdit)){
      items = manager.getAllFeedItems();
    }
    System.out.println(items.get(0).contents.get(0).description);

    File file = new File(dir, "InstagramEdit.txt");

    SFMUtils.outputToFile(items, file);

  }
}
