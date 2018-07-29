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

    facebookTest();
//    twitterTest();
    testSFMForTwitter();
    System.out.println("check output logs from /SocialFeedManager/output folder");
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

  public static void testSFMForTwitter(){
      SocialFeedManager manager = new  SocialFeedManager();

      TwitterTester tester = new TwitterTester(manager);

      // please uncomment the following function to test specific functionalaities.

      tester.testGetAllFeed(); // Fet all social feed data
//      tester.testGetTwitterFeed(); // get twitter feed data
//      tester.testTwitterPost();  // post to twitter
//      tester.testTwitterEditPost();  // edit twitter post
//      tester.testTwitterFavorite(); // favorite twitter post
//      tester.testTwitterRetweet(); // retiweet
//      tester.testTwitterAddComment(); // add comment
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
