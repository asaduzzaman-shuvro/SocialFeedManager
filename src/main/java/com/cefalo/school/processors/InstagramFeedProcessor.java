package com.cefalo.school.processors;

import com.cefalo.school.application.AccountManager;
import com.cefalo.school.mapper.FacebookFeedMapper;
import com.cefalo.school.mapper.InstagramFeedMapper;
import com.cefalo.school.model.FeedItem;
import com.cefalo.school.operators.FacebookOperator;
import com.cefalo.school.operators.InstagramOperator;

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
    public List<FeedItem> getFeedItems() {

        if(feedOperator.getFeed(AccountManager.getInstance().getAuthTokenByIdentifier(applicationIdentifier))) {
            feedItems = feedMapper.getProcessedFeedItems(applicationIdentifier, feedOperator.jsonObject);
            return feedItems;
        }
        return null;
    }

    @Override
    public void updateFeedItem(FeedItem feedItem, Enum action) {

    }

    @Override
    public boolean postUpdate(FeedItem item) {
        return false;
    }

    @Override
    public UUID getApplicationIdentifier() {
        return applicationIdentifier;
    }
}
