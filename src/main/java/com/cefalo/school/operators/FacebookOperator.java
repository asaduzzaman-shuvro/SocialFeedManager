package com.cefalo.school.operators;

import com.cefalo.school.model.Content;
import com.cefalo.school.model.ContentType;
import com.cefalo.school.model.FBActionType;
import com.cefalo.school.model.FacebookFeedItem;
import com.cefalo.school.model.FeedItem;
import com.cefalo.school.model.SFMAction;
import com.thirdparty.api.FacebookApi;
import org.json.JSONArray;
import org.json.JSONObject;

public class FacebookOperator implements FeedOperator {
    public JSONObject jsonObject = null;

    public boolean postItem(JSONObject item) {
        if (jsonObject != null){
            JSONArray array = jsonObject.getJSONArray("data");
            int i=0;
            for (Object object : array) {
                JSONObject jsonItem = (JSONObject) object;
                if(jsonItem.getString("id").equals(item.getString("id"))){
                    array.put(i, item);
                    return true;
                }
                i++;
            }
            array.put(item);
            // post to FB api
            return true;
        }
        return false;
    }

    @Override
    public boolean getFeed(String authToken) {
        if(jsonObject == null) {
            jsonObject = FacebookApi.getFeeds(authToken);
        }
        return jsonObject != null;
    }

    public FeedItem addAction(FeedItem item, SFMAction action){
        FacebookFeedItem fbItem = (FacebookFeedItem) item;
        if(action.actionType != FBActionType.COMMENT) {
            int currentReactionValue = 0;
            if(fbItem.reactions.containsKey(action.actionType.toString().toLowerCase())){
                currentReactionValue = fbItem.reactions.get(action.toString().toLowerCase());
            }
            fbItem.reactions.put(action.actionType.toString().toLowerCase(),
                currentReactionValue + 1);
        }else {
            FeedItem comment = new FacebookFeedItem();
            comment.contents.add(new Content(ContentType.TEXT, "", action.description));
            ((FacebookFeedItem) item).comments.add(comment);
        }
        return fbItem;
    }

    public FeedItem updateComment(FeedItem item, String id, String msg){
        FacebookFeedItem newItem = (FacebookFeedItem)item;
        if(newItem != null){
            if(newItem.comments.size() > 0) {
                FeedItem targetComment = newItem.comments.stream()
                    .filter(comment -> id.equals(comment.identifier))
                    .findAny()
                    .orElse(null);
                if (targetComment != null) {
                    targetComment.contents.get(0).description = msg;
                }
            }
        }
        return newItem;
    }
}
