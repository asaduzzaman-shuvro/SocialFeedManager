package com.cefalo.school.application;

import com.cefalo.school.factories.FeedProcessorFactory;
import com.cefalo.school.model.FeedItem;
import com.cefalo.school.processors.FeedProcessor;

import java.util.List;

public class SocialFeedManager {
    private List<FeedItem> allFeedItems;
    private List<FeedProcessor> processors;

    public SocialFeedManager() {
        List<Application> applications = AccountManager.getInstance().getSupportedApplications();
        for (Application application : applications) {
            processors.add(FeedProcessorFactory.getFeedOperator(application.getApplicationType()));
        }
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

