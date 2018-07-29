package com.cefalo.school;

import com.cefalo.school.application.Application;
import com.cefalo.school.application.SocialFeedManager;
import com.cefalo.school.model.*;
import com.cefalo.school.services.SFMUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TwitterTester {

    private SocialFeedManager socialFeedManager;

    public TwitterTester(SocialFeedManager socialFeedManager) {
        this.socialFeedManager = socialFeedManager;
    }

    public void testGetAllFeed(){
        List<FeedItem> items = socialFeedManager.getAllFeedItems();

        if(items.size() > 0){
            File dir = new File("output");
            dir.mkdirs();
            File file = new File(dir, "1.Twitter_get_all.txt");
            SFMUtils.outputToFile(items, file, socialFeedManager);
            return;
        }
        System.out.println("no data to show for Twitter: testGetAllFeed");
    }

    public void testGetTwitterFeed(){
        List<UUID> identifiers = new ArrayList<>();
        identifiers.add(socialFeedManager.getApplicationIdentifiers().get(1));
        List<FeedItem> items = socialFeedManager.getFeedItemsForApplications(identifiers);

        if(items.size() > 0){
            File dir = new File("output");
            dir.mkdirs();
            File file = new File(dir, "2.Twitter_get_twitter.txt");
            SFMUtils.outputToFile(items, file, socialFeedManager);
            return;
        }
        System.out.println("no data to show for Twitter: testGetAllFeed");
    }

    public void testTwitterPost(){

        FeedItem itemToPost = new FeedItem();
        itemToPost.contents.add(new Content(ContentType.TEXT, "", "My first status from SFM"));

        List<UUID> identifiers = new ArrayList<>();
        identifiers.add(socialFeedManager.getApplicationIdentifiers().get(1));

        List<FeedItem> items = new ArrayList<>();
        items = socialFeedManager.getFeedItemsForApplications(identifiers);
        if(socialFeedManager.postItem(itemToPost, identifiers)){
            items = socialFeedManager.getAllFeedItems();
        }

        File dir = new File("output");
        dir.mkdirs();
        File file1 = new File(dir, "3.Twitter_post.txt");

        SFMUtils.outputToFile(items, file1, socialFeedManager);
    }

    public void testTwitterEditPost(){
        FeedItem itemToPost = new FeedItem();
        itemToPost.contents.add(new Content(ContentType.TEXT, "", "My first status from SFM"));

        String identifierToEdit = "";

        List<UUID> identifiers = new ArrayList<>();
        identifiers.add(socialFeedManager.getApplicationIdentifiers().get(1));
        List<FeedItem> items = socialFeedManager.getFeedItemsForApplications(identifiers);

        if(socialFeedManager.postItem(itemToPost, identifiers)){
            identifierToEdit = itemToPost.identifier;
            items = socialFeedManager.getFeedItemsForApplications(identifiers);
        }

        File dir = new File("output");
        dir.mkdirs();
        File file1 = new File(dir, "4.twitter_edit_before.txt");

        SFMUtils.outputToFile(items, file1, socialFeedManager);

        if(!identifierToEdit.isEmpty()){
            String finalIdentifierToEdit = identifierToEdit;
            FeedItem feedItem = items.stream().filter(fIem->{
                return fIem.identifier == finalIdentifierToEdit;
            }).findAny().orElse(null);
            if(feedItem != null){
                for (Content content : feedItem.contents) {
                    if(content.contentType == ContentType.TEXT){
                        content.description = "this is the changed text for this post";
                    }
                }

                if(socialFeedManager.editFeedItem(feedItem)){
                    items = socialFeedManager.getFeedItemsForApplications(identifiers);
                }

                File file = new File(dir, "5.twitter_edit_after.txt");

                SFMUtils.outputToFile(items, file, socialFeedManager);
            }
        }
    }

    public void testTwitterRetweet(){
        List<UUID> identifiers = new ArrayList<>();
        identifiers.add(socialFeedManager.getApplicationIdentifiers().get(1));
        List<FeedItem> items = socialFeedManager.getFeedItemsForApplications(identifiers);

        if(items.size() > 0){
            File dir = new File("output");
            dir.mkdirs();
            File file = new File(dir, "6.Twitter_retweet_before.txt");
            SFMUtils.outputToFile(items, file, socialFeedManager);

            FeedItem itemToPost = items.get(0);
            if(socialFeedManager.addAction(itemToPost, new SFMAction(TwitterActionType.RETWEET))){
                items = socialFeedManager.getAllFeedItems();
            }

            if(socialFeedManager.postItem(itemToPost, identifiers)){
                items = socialFeedManager.getFeedItemsForApplications(identifiers);
            }

            File file1 = new File(dir, "7.Twitter_retweet_after.txt");

            SFMUtils.outputToFile(items, file1, socialFeedManager);
            return;
        }
        System.out.println("no data to show for Twitter: testGetAllFeed");
    }

    public void testTwitterFavorite(){
        List<UUID> identifiers = new ArrayList<>();
        identifiers.add(socialFeedManager.getApplicationIdentifiers().get(1));
        List<FeedItem> items = socialFeedManager.getFeedItemsForApplications(identifiers);

        if(items.size() > 0){
            File dir = new File("output");
            dir.mkdirs();
            File file = new File(dir, "8.Twitter_favorite_before.txt");
            SFMUtils.outputToFile(items, file, socialFeedManager);

            FeedItem itemToPost = items.get(0);
            if(socialFeedManager.addAction(itemToPost, new SFMAction(TwitterActionType.FAVORITE))){
                items = socialFeedManager.getAllFeedItems();
            }

            if(socialFeedManager.postItem(itemToPost, identifiers)){
                items = socialFeedManager.getFeedItemsForApplications(identifiers);
            }

            File file1 = new File(dir, "9.Twitter_Favorite_after.txt");

            SFMUtils.outputToFile(items, file1, socialFeedManager);
            return;
        }
        System.out.println("no data to show for Twitter: testGetAllFeed");
    }

    public void testTwitterAddComment(){
        List<UUID> identifiers = new ArrayList<>();
        identifiers.add(socialFeedManager.getApplicationIdentifiers().get(1));
        List<FeedItem> items = socialFeedManager.getFeedItemsForApplications(identifiers);

        if(items.size() > 0){
            File dir = new File("output");
            dir.mkdirs();
            File file = new File(dir, "10.Twitter_add_comment_before.txt");
            SFMUtils.outputToFile(items, file, socialFeedManager);

            FeedItem itemToPost = items.get(0);
            if(socialFeedManager.addAction(itemToPost, new SFMAction(TwitterActionType.COMMENT, "Test comment from SFM"))){
                items = socialFeedManager.getFeedItemsForApplications(identifiers);
            }

            File file1 = new File(dir, "11.Twitter_add_comment_after.txt");

            SFMUtils.outputToFile(items, file1, socialFeedManager);
            return;
        }
        System.out.println("no data to show for Twitter: testGetAllFeed");
    }
}
