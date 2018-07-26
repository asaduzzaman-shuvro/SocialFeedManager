package com.cefalo.school.application;

import com.cefalo.school.factories.FeedProcessorFactory;
import com.cefalo.school.model.FeedItem;
import com.cefalo.school.processors.FeedProcessor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class SocialFeedManager {
    private List<FeedItem> allFeedItems = new ArrayList<>();
    private List<FeedProcessor> processors = new ArrayList<>();

    public SocialFeedManager() {
        List<Application> applications = AccountManager.getInstance().getSupportedApplications();
        for (Application application : applications) {
            processors.add(FeedProcessorFactory.getFeedProcessor(application));
        }
    }

    private FeedProcessor getProcessor(UUID appIdentifier){
        for (FeedProcessor processor : processors) {
            if (processor.getApplicationIdentifier() == appIdentifier){
                return processor;
            }
        }
        return null;
    }

    public List<FeedItem> getAllFeedItems(){

        for (FeedProcessor processor: processors) {
            allFeedItems.addAll(processor.getFeedItems());
        }
        if (allFeedItems != null){
            allFeedItems.sort(((o2, o1) -> o1.publishedDate.compareTo(o2.publishedDate)));
        }
        System.out.println("all feeds" + allFeedItems);
        for (FeedItem item: allFeedItems) {
            System.out.println("item.publishedDate " + item.publishedDate + " item.appType "+ AccountManager.getInstance().getApplicationTypeByIdentifier(item.applicationIdentifier)+ " item.contents " + item.contents);
        }
        return allFeedItems;
    }

    public boolean editFeedItem(FeedItem item){

        return false;
    }

    public boolean postItem(FeedItem item){
        return false;
    }
}

