package com.cefalo.school.application;

import com.cefalo.school.factories.FeedProcessorFactory;
import com.cefalo.school.model.FeedItem;
import com.cefalo.school.model.ActionType;
import com.cefalo.school.model.SFMAction;
import com.cefalo.school.processors.FeedProcessor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class SocialFeedManager {
    private List<FeedItem> allFeedItems = new ArrayList<>();
    private List<FeedProcessor> processors = new ArrayList<>();
    private AccountManager accountManager = new AccountManager();

    public SocialFeedManager() {
        List<Application> applications = accountManager.getSupportedApplications();
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

    public List<UUID> getApplicationIdentifiers(){
        return accountManager.getSupportedApplications().stream().map(app -> app.getApplicationIdentifier()).collect(Collectors.toList());
    }

    public List<FeedItem> getAllFeedItems(){

        if (allFeedItems.size() > 0) allFeedItems.clear();
        List<Application> applications = accountManager.getSupportedApplications();
        for (FeedProcessor processor: processors) {
            allFeedItems.addAll(processor.getFeedItems(accountManager.getAuthTokenByIdentifier(processor.getApplicationIdentifier())));
        }
        if (allFeedItems != null){
            allFeedItems.sort(((o2, o1) -> o1.publishedDate.compareTo(o2.publishedDate)));
        }
        System.out.println("all feeds" + allFeedItems);
        for (FeedItem item: allFeedItems) {
            System.out.println("item.publishedDate " + item.publishedDate + " item.appType "+ accountManager.getApplicationTypeByIdentifier(item.applicationIdentifier)+ " item.userID " + item.userID);
        }
        return allFeedItems;
    }

    public boolean addAction(FeedItem item, SFMAction action){
        FeedProcessor processor = getProcessor(item.applicationIdentifier);

        return processor.addAction(item, action, accountManager.getAuthTokenByIdentifier(item.applicationIdentifier));
    }

    public boolean editFeedItem(FeedItem item){
        if(item.userID.equals(accountManager.getApplicationUserIdByIdentifier(item.applicationIdentifier))){
            // edit supported
            return postItem(item, new ArrayList<UUID>(){{add(item.applicationIdentifier);}});
        }
        return false;
    }

    public boolean postItem(FeedItem item, List<UUID>appIdentifiers){
        boolean success = false;
        for (UUID id: appIdentifiers) {
            FeedProcessor processor = getProcessor(id);
            if(item.userID.isEmpty()){
                item.userID = accountManager.getApplicationUserIdByIdentifier(id);
            }
            success = processor.postUpdate(item, accountManager.getAuthTokenByIdentifier(id));
        }
        return success;
    }
}

