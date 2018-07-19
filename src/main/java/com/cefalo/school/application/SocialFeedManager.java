package com.cefalo.school.application;

import com.cefalo.school.Factories.FeedProcessorFactory;
import com.cefalo.school.Model.FeedItem;

import java.util.List;

public class SocialFeedManager {
    public List<FeedItem> feedItems;

    public void getFeedOperators(){
        for (Application application: AccountManager.getInstance().getListItems()) {
            FeedProcessorFactory.getFeedOperator(application.getApplicationType());
        }
    }
}
