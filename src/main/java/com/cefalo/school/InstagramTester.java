package com.cefalo.school;

import com.cefalo.school.application.SocialFeedManager;
import com.cefalo.school.model.Content;
import com.cefalo.school.model.ContentType;
import com.cefalo.school.model.FeedItem;
import com.cefalo.school.services.SFMUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class InstagramTester {
    private SocialFeedManager socialFeedManager;

    public InstagramTester(SocialFeedManager socialFeedManager) {
        this.socialFeedManager = socialFeedManager;
    }

    public void testGetAllFeed(){
        List<FeedItem> items = socialFeedManager.getAllFeedItems();
        if(items.size() > 0){
            File dir = new File("output");
            dir.mkdirs();
            File file = new File(dir, "AllFeeds.txt");
            SFMUtils.outputToFile(items, file, socialFeedManager);
            return;
        }
    }

    public void testGetInstagramFeed(){
        List<UUID> identifiers = new ArrayList<>();
        identifiers.add(socialFeedManager.getApplicationIdentifiers().get(2));
        List<FeedItem> items = socialFeedManager.getFeedItemsForApplications(identifiers);

        if(items.size() > 0){
            File dir = new File("output");
            dir.mkdirs();
            File file = new File(dir, "InstagramFeedOnly.txt");
            SFMUtils.outputToFile(items, file, socialFeedManager);
            return;
        }
    }

    public void testInstagramNewPost(){

        FeedItem itemToPost = new FeedItem();
        itemToPost.contents.add(new Content(ContentType.TEXT, "", "Instagram Post"));

        List<UUID> identifiers = new ArrayList<>();
        identifiers.add(socialFeedManager.getApplicationIdentifiers().get(1));

        List<FeedItem> items = new ArrayList<>();
        if(socialFeedManager.postItem(itemToPost, identifiers)){
            items = socialFeedManager.getAllFeedItems();
        }

        File dir = new File("output");
        dir.mkdirs();
        File file1 = new File(dir, "NewInstagramPost.txt");

        SFMUtils.outputToFile(items, file1, socialFeedManager);
    }

    public void instagramEditPost(){
        List<UUID> identifiers = new ArrayList<>();
        identifiers.add(socialFeedManager.getApplicationIdentifiers().get(2));

        FeedItem itemToPost = new FeedItem();
        itemToPost.contents.add(new Content(ContentType.TEXT, "", "Insatagram Status"));

        List<FeedItem> items = new ArrayList<>();
        if(socialFeedManager.postItem(itemToPost, identifiers)){
            items = socialFeedManager.getAllFeedItems();
        }

        File dir = new File("output");
        dir.mkdirs();
        File file1 = new File(dir, "BeforeEditInstagram.txt");

        SFMUtils.outputToFile(items, file1,socialFeedManager);

        FeedItem itemToEdit = items.get(0);
        for (Content content : itemToEdit.contents) {
            if(content.contentType == ContentType.TEXT){
                content.description = "eited instagram status";
            }
        }

        if(socialFeedManager.editFeedItem(itemToEdit)){
            items = socialFeedManager.getAllFeedItems();
        }

        File file = new File(dir, "AfterEditInstagramEdit.txt");

        SFMUtils.outputToFile(items, file,socialFeedManager);

    }



}
