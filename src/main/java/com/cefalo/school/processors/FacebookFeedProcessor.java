package com.cefalo.school.processors;

import com.cefalo.school.mapper.FacebookFeedMapper;
import com.cefalo.school.model.FeedItem;
import com.cefalo.school.model.SFMAction;
import com.cefalo.school.operators.FacebookOperator;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.json.JSONObject;

public class FacebookFeedProcessor implements FeedProcessor {

    FacebookOperator feedOperator = new FacebookOperator();
    FacebookFeedMapper feedMapper = new FacebookFeedMapper();
    private UUID applicationIdentifier;
    public List<FeedItem> feedItems = new ArrayList<>();

    public FacebookFeedProcessor(UUID appIdentifier){
        this.applicationIdentifier = appIdentifier;
    }

    @Override
    public List<FeedItem> getFeedItems(String authToken){
        if(feedOperator.getFeed(authToken)) {
            feedItems = feedMapper.getProcessedFeedItems(applicationIdentifier, feedOperator.jsonObject);
            return feedItems;
        }
        return null;
    }


    @Override
    public boolean addAction(FeedItem feedItem, SFMAction action, String authToken, String userId,
        String displayName){
        return postUpdate(feedOperator.addAction(feedItem, action, userId, displayName), authToken);
    }

    @Override
    public boolean postUpdate(FeedItem item, String authToken) {
        JSONObject object = feedMapper.mapFeedItemToJSON(item);
        return feedOperator.postItem(object);
    }

    @Override
    public UUID getApplicationIdentifier() {
        return applicationIdentifier;
    }

    public boolean updateComment(FeedItem item, String id, String msg, String authToken){
        return postUpdate(feedOperator.updateComment(item, id, msg), authToken);
    }
}
