package com.cefalo.school.processors;

import com.cefalo.school.mapper.FacebookFeedMapper;
import com.cefalo.school.model.FacebookFeedItem;
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
    public boolean addAction(FeedItem feedItem, SFMAction action, String authToken){
        return postUpdate(feedOperator.addAction(feedItem, action), authToken);
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

    public void updateComment(UUID uuid, String id, String msg){
        feedItems.forEach(feedItem -> {
            FacebookFeedItem newItem = null;
            if(feedItem instanceof FacebookFeedItem) {
                newItem = (FacebookFeedItem)feedItem;
            }
            if(newItem != null){
                if(newItem.comments.size() > 0) {
                    FeedItem targetComment = newItem.comments.stream()
                            .filter(comment -> id.equals(comment.identifier))
                            .findAny()
                            .orElse(null);
                    if (targetComment != null) {
                        targetComment.contents.get(0).value = msg;
                    }
                }
            }

        });

    }
}
