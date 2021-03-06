package com.cefalo.school.application;

import com.cefalo.school.factories.FeedProcessorFactory;
import com.cefalo.school.model.FacebookFeedItem;
import com.cefalo.school.model.FeedItem;
import com.cefalo.school.model.SFMAction;
import com.cefalo.school.processors.FacebookFeedProcessor;
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
        FeedProcessor processor = processors.stream().filter(feedProcessor->{
            return feedProcessor.getApplicationIdentifier() == appIdentifier;
        }).findAny().orElse(null);
        if(processor != null){
            return processor;
        }
        return null;
    }

    public List<UUID> getApplicationIdentifiers(){
        return accountManager.getSupportedApplications().stream().map(app -> app.getApplicationIdentifier()).collect(Collectors.toList());
    }

    public String getApplicationName(UUID appIdentifier){
        return accountManager.getApplicationNameByIdentifier(appIdentifier);
    }

    public List<FeedItem>getFeedItemsForApplications(List<UUID>appIdentifiers){
        if (allFeedItems.size() > 0) allFeedItems.clear();

        for (UUID appIdentifier : appIdentifiers) {
            FeedProcessor processor = getProcessor(appIdentifier);
            allFeedItems.addAll(processor.
                    getFeedItems(accountManager.getAuthTokenByIdentifier(processor.getApplicationIdentifier())));
        }
        if (allFeedItems != null){
            allFeedItems.sort(((o2, o1) -> o1.publishedDate.compareTo(o2.publishedDate)));
        }

        return allFeedItems;
    }


    public List<FeedItem> getAllFeedItems(){

        if (allFeedItems.size() > 0) allFeedItems.clear();
        for (FeedProcessor processor: processors) {
            allFeedItems.addAll(processor.getFeedItems(accountManager.getAuthTokenByIdentifier(processor.getApplicationIdentifier())));
        }
        if (allFeedItems != null){
            allFeedItems.sort(((o2, o1) -> o1.publishedDate.compareTo(o2.publishedDate)));
        }
        return allFeedItems;
    }

    public boolean addAction(FeedItem item, SFMAction action){
        FeedProcessor processor = getProcessor(item.applicationIdentifier);
        return processor.addAction(item, action,
            accountManager.getAuthTokenByIdentifier(item.applicationIdentifier),
            accountManager.getApplicationUserIdByIdentifier(item.applicationIdentifier),
            accountManager.getApplicationUserDisplayNameByIdentifier(item.applicationIdentifier));
    }

    public boolean editFeedItem(FeedItem item){
        if(item.userID.equals(accountManager.getApplicationUserIdByIdentifier(item.applicationIdentifier))){
            return postItem(item, new ArrayList<UUID>(){{add(item.applicationIdentifier);}});
        }
        return false;
    }

    public boolean editComment(FeedItem item, String commentId, String newMsg){
        FacebookFeedProcessor processor = (FacebookFeedProcessor) getProcessor(item.applicationIdentifier);
        FacebookFeedItem itemToEdit = (FacebookFeedItem) item;
        FeedItem targetComment = ((FacebookFeedItem) item).comments.stream()
            .filter(comment->{
                return comment.identifier.equals(commentId);
            }).findAny().orElse(null);
        if(targetComment.userID.equals(accountManager.getApplicationUserIdByIdentifier(item.applicationIdentifier))){
            // edit supported
            return processor.updateComment(item, commentId, newMsg, accountManager.getAuthTokenByIdentifier(item.applicationIdentifier));
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
            if(item.displayName.isEmpty()){
                item.displayName = accountManager.getApplicationUserDisplayNameByIdentifier(id);
            }
            success = processor.postUpdate(item, accountManager.getAuthTokenByIdentifier(id));
        }
        return success;
    }
}

