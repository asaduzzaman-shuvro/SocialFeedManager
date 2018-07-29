package com.cefalo.school.operators;

import com.cefalo.school.model.*;
import com.thirdparty.api.TwitterApi;
import org.json.JSONArray;
import org.json.JSONObject;

public class TwitterOperator implements FeedOperator {
    public JSONObject jsonObject = null;

    public boolean postItem(JSONObject item) {
        if (jsonObject != null){
            JSONArray array = jsonObject.getJSONArray("data");
            int i = 0;
            for (Object object : array) {
                JSONObject jsonItem = (JSONObject) object;
                if(jsonItem.getString("id_str").equals(item.getString("id_str"))){
                    array.put(i, item);
                    return true;
                }
                i++;
            }
            array.put(item);
            // post to twitter api
            return true;
        }
        return false;
    }

    @Override
    public boolean getFeed(String authToken) {
        if(jsonObject == null) {
            jsonObject = TwitterApi.getFeeds(authToken);
        }
        return jsonObject != null;
    }

    public FeedItem addAction(FeedItem item, SFMAction action, String userId, String displayName){
        TwitterFeedItem tweetItem = (TwitterFeedItem) item;
        if(action.actionType == TwitterActionType.FAVORITE) {
            tweetItem.favoriteCount += 1;
        }
        else if(action.actionType == TwitterActionType.RETWEET){
            tweetItem.retweetCount += 1;
        } else{
            Comment comment = new Comment(action.description);
            comment.commenteId = userId;
            comment.commenteDisplayName = displayName;
            tweetItem.comments.add(comment);
        }
        return tweetItem;
    }
}
