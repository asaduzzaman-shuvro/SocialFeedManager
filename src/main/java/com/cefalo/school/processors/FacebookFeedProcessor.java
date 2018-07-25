package com.cefalo.school.processors;

import com.cefalo.school.mapper.FacebookFeedMapper;
import com.cefalo.school.mapper.FeedMapper;
import com.cefalo.school.model.FeedItem;
import com.cefalo.school.operators.FacebookOperator;
import com.cefalo.school.operators.FeedOperator;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class FacebookFeedProcessor implements FeedProcessor {

    FacebookOperator feedOperator = new FacebookOperator();
    FacebookFeedMapper feedMapper = new FacebookFeedMapper();
    public List<FeedItem> fbFeedItems = new ArrayList<>();

    @Override
    public List<FeedItem> getFeedItems(UUID applicationIdentifier){
        if(feedOperator.getFeed()) {
            fbFeedItems = feedMapper.getProcessedFeedItems(applicationIdentifier, feedOperator.jsonObject);
            return fbFeedItems;
        }
        return null;
    }


    @Override
    public void updateFeedItem(UUID uuid, FeedItem feedItem, Enum action){
        feedItem.reactions.put(action.toString().toLowerCase(),
                feedItem.reactions.get(action.toString().toLowerCase()) + 1);
    }

    public void updateComment(UUID uuid, String id, String msg){
        fbFeedItems.forEach(feedItem -> {
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
