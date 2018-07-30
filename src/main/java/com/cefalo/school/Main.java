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
//    instagramTest();
    postToAllApplication();
    System.out.println("check output logs from /SocialFeedManager/output folder");

  }

  public static void postToAllApplication(){
    SocialFeedManager manager = new SocialFeedManager();
    manager.getAllFeedItems();


    FeedItem itemToPost = new FeedItem();
    itemToPost.contents.add(new Content(ContentType.TEXT, "", "This is test post to all application from SFM"));


    List<FeedItem> items = new ArrayList<>();
    if(manager.postItem(itemToPost, manager.getApplicationIdentifiers())){
      items = manager.getAllFeedItems();
    }

    File dir = new File("output");
    dir.mkdirs();
    File file1 = new File(dir, "0.Post_to_all_application.txt");

    SFMUtils.outputToFile(items, file1, manager);


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
    File file1 = new File(dir, "1.Facebook_post.txt");

    SFMUtils.outputToFile(items, file1, manager);

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

    File file = new File(dir, "2.Facebook_edit_post.txt");

    SFMUtils.outputToFile(items, file, manager);

    // add like to this post

    if(manager.addAction(itemToEdit, new SFMAction(FBActionType.LIKE))){
      items = manager.getAllFeedItems();
    }


    File file2= new File(dir, "3.Facebook_add_like.txt");

    SFMUtils.outputToFile(items, file2, manager);

    // add comment to this post

    if(manager.addAction(itemToEdit, new SFMAction(FBActionType.COMMENT, "First comment on the test post"))){
      items = manager.getAllFeedItems();
    }

    File file3= new File(dir, "4.Facebook_add_comment.txt");

    SFMUtils.outputToFile(items, file3, manager);

    /* edit comment to this post */

    FacebookFeedItem targetItem = (FacebookFeedItem)items.get(0);

    String targetCommentId = targetItem.comments.get(0).identifier;

    if(manager.editComment(targetItem, targetCommentId, "Comment edited..")){
      items = manager.getAllFeedItems();
    }

    File file4= new File(dir, "5.Facebook_edit_comment.txt");

    SFMUtils.outputToFile(items, file4, manager);


  }

  public static void testingForTwitter(){

    SocialFeedManager manager = new SocialFeedManager();
    TwitterTester tester = new TwitterTester(manager);

//    uncomment the functiona you want to test

    tester.testGetAllFeed(); // get all feed
//    tester.testGetTwitterFeed(); // get twitter specific feed
//    tester.testTwitterPost(); // post to twitter
//    tester.testTwitterEditPost(); //edit post
//    tester.testTwitterFavorite(); // favorite post
//    tester.testTwitterRetweet(); //retweet
//    tester.testTwitterAddComment(); // add comment 
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

    SFMUtils.outputToFile(items, file1,manager);


    FeedItem itemToEdit = items.get(0);
    for (Content content : itemToEdit.contents) {
      if(content.contentType == ContentType.TEXT){
        content.description = "eited instagram status";
      }
    }

    if(manager.editFeedItem(itemToEdit)){
      items = manager.getAllFeedItems();
    }

    File file = new File(dir, "InstagramEdit.txt");

    SFMUtils.outputToFile(items, file,manager);

  }
}
