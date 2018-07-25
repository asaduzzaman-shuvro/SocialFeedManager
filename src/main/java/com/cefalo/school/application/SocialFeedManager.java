package com.cefalo.school.application;

import com.cefalo.school.factories.FeedProcessorFactory;
import com.cefalo.school.model.FeedItem;
import com.cefalo.school.processors.FeedProcessor;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public class SocialFeedManager {
    private List<FeedItem> allFeedItems;
    private List<FeedProcessor> processors;

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
        return null;
    }

    public boolean editFeedItem(FeedItem item){

        return false;
    }

    public boolean postItem(FeedItem item){
        return false;
    }
}

