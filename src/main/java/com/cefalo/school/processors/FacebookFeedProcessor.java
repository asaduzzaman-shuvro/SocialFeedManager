package com.cefalo.school.processors;

import com.cefalo.school.mapper.FacebookFeedMapper;
import com.cefalo.school.model.FeedItem;
import com.cefalo.school.operators.FacebookOperator;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
    public void updateFeedItem(FeedItem feedItem, Enum action){
        feedItem.reactions.put(action.toString().toLowerCase(),
                feedItem.reactions.get(action.toString().toLowerCase()) + 1);
    }

    @Override
    public boolean postUpdate(FeedItem item, String authToken) {
        return false;
    }

    @Override
    public UUID getApplicationIdentifier() {
        return applicationIdentifier;
    }

    public void updateComment(UUID uuid, String id, String msg){
        feedItems.forEach(feedItem -> {
            if(feedItem.comments.size() > 0) {
                FeedItem targetComment = feedItem.comments.stream()
                    .filter(comment -> id.equals(comment.identifier))
                    .findAny()
                    .orElse(null);
                if (targetComment != null) {
                    targetComment.contents.get(0).value = msg;
                }
            }
        });

    }
}
