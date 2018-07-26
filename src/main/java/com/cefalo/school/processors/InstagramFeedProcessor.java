package com.cefalo.school.processors;

import com.cefalo.school.mapper.InstagramFeedMapper;
import com.cefalo.school.model.FeedItem;
import com.cefalo.school.operators.InstagramOperator;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class InstagramFeedProcessor implements FeedProcessor{
    InstagramOperator feedOperator = new InstagramOperator();
    InstagramFeedMapper feedMapper = new InstagramFeedMapper();
    private UUID applicationIdentifier;
    public List<FeedItem> feedItems = new ArrayList<>();

    public InstagramFeedProcessor(UUID appIdentifier){
        this.applicationIdentifier = appIdentifier;
    }

    @Override
    public List<FeedItem> getFeedItems(String authToken) {

        if(feedOperator.getFeed(authToken)) {
            feedItems = feedMapper.getProcessedFeedItems(applicationIdentifier, feedOperator.jsonObject);
            return feedItems;
        }
        return null;
    }

    @Override
    public void updateFeedItem(FeedItem feedItem, Enum action) {

    }

    @Override
    public boolean postUpdate(FeedItem item, String authToken) {
        JSONObject object = feedMapper.mapFeedItemToJSON(item);
        return feedOperator.postUpdate(object);
    }

    @Override
    public UUID getApplicationIdentifier() {
        return applicationIdentifier;
    }
}
