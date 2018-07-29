package com.cefalo.school.mapper;

import com.cefalo.school.model.Content;
import com.cefalo.school.model.ContentType;
import com.cefalo.school.model.FacebookFeedItem;
import com.cefalo.school.model.FeedItem;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class FacebookFeedMapper implements FeedMapper {
    Map<String, ContentType> keyMap = new HashMap<String, ContentType>(){{
        put("status", ContentType.TEXT);
        put("TEXT", ContentType.TEXT);
        put("link", ContentType.URL);
        put("URL", ContentType.URL);
        put("video", ContentType.VIDEO);
        put("VIDEO", ContentType.VIDEO);
        put("photo", ContentType.PICTURE);
        put("PICTURE", ContentType.PICTURE);
    }};

    public List<FeedItem> getProcessedFeedItems(UUID applicationIdentifier, JSONObject jsonObject) {

        JSONArray data = jsonObject.getJSONArray("data");

        List<FeedItem> feedItemList = new ArrayList<FeedItem>();


        List<String> reactionKeyList = new ArrayList<String>(){{
            add("like");
            add("wow");
            add("love");
            add("haha");
            add("sad");
            add("angry");
        }};

        for (Object item : data) {

            JSONObject object = (JSONObject) item;

            FacebookFeedItem feedItem = new FacebookFeedItem();
            feedItem.identifier = object.getString("id");
            feedItem.applicationIdentifier = applicationIdentifier;
            try {
                feedItem.publishedDate = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ssZ", Locale.ENGLISH)
                    .parse(object.getString("created_time")) ;
            }catch (Exception e) {
                e.printStackTrace();
            }

            if(object.has("from")){
                feedItem.userID = object.getJSONObject("from").getString("id");
                feedItem.displayName = object.getJSONObject("from").getString("name");
            }

            if(object.has("type")) {
                String feedType = object.getString("type");
                feedItem.contents.add(new Content(keyMap.get(feedType), "",
                    object.has("message") ? object.getString("message") : ""));
                if (object.has("attachments")){
                    JSONArray attachments = object.getJSONObject("attachments").getJSONArray("data");
                    mapAttachments(feedItem, attachments, object.getString("type"));
                }
            }

            reactionKeyList.forEach(reaction->{
                if(object.has(reaction)){
                    feedItem.reactions.put(reaction, object.getJSONObject(reaction)
                        .getJSONObject("summary").getInt("total_count"));
                }
            });

            if(object.has("comments")){
                List<FeedItem> comment = getProcessedFeedItems(applicationIdentifier, object.getJSONObject("comments"));
                feedItem.comments.addAll(comment);
            }

            feedItemList.add(feedItem);
        }

        return feedItemList;
    }

    private JSONObject mapAttachments(FeedItem item, JSONArray attachments, String itemType){
        for (Object attachment : attachments) {
            JSONObject object = (JSONObject) attachment;
            if(object.getString("type").equals("photo") || object.getString("type").equals("new_album")){
                if(object.has("subattachments")){
                    item.contents.add(new Content(ContentType.PICTURE, object.getString("url"),
                        object.has("description")?object.getString("description"):""));
                }else{
                    item.contents.add(new Content(ContentType.PICTURE, object.getJSONObject("media")
                        .getJSONObject("image").getString("src"),
                        object.has("description")?object.getString("description"):""));
                }

            }else{
                item.contents.add(new Content(keyMap.get(itemType),object.getString("url"),
                    object.has("description")?object.getString("description"):""));
            }
        }
        return null;
    }

    private FacebookFeedItem mapFeedItemToFacebookFeedItem(FeedItem item){
        if(item instanceof FacebookFeedItem){
            FacebookFeedItem fbItem = (FacebookFeedItem)item;
            return fbItem;
        }else{
            FacebookFeedItem fbItem = new FacebookFeedItem();
            fbItem.identifier = item.identifier;
            fbItem.contents = item.contents;
            fbItem.displayName = item.displayName;
            fbItem.userID = item.displayName;
            fbItem.applicationIdentifier = item.applicationIdentifier;
            fbItem.publishedDate = item.publishedDate;

            return fbItem;
        }
    }

    @Override
    public JSONObject mapFeedItemToJSON(FeedItem item)
    {
        FacebookFeedItem fbItem = mapFeedItemToFacebookFeedItem(item);

        JSONObject object = new JSONObject();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ssZ");
        object.put("created_time", df.format(fbItem.publishedDate));
        object.put("id", fbItem.identifier);
        JSONObject user = new JSONObject();
        user.put("id", fbItem.userID);
        user.put("name", fbItem.displayName);
        object.put("from", user);

        int i=0;
        for (Content content:fbItem.contents) {
            if(i==0){
                object.put("type", content.contentType.toString());
                object.put("message", content.description);
            }else{
                JSONArray attachments = new JSONArray();
                JSONObject attach = new JSONObject();
                attach.put("type", content.contentType.toString());
                attach.put("description", content.description);
                JSONObject target = new JSONObject();
                target.put("url", content.value);
                attach.put("target", target);
                attachments.put(attach);
                object.put("attachments", attachments);
            }
        }

        JSONObject commentObj = new JSONObject();
        JSONArray comments = new JSONArray();
        commentObj.put("data", comments);

        for (FeedItem comment : fbItem.comments) {
            comments.put(mapFeedItemToJSON(comment));
        }

        if(comments.length() > 0){
            object.put("comments", commentObj);
        }

        fbItem.reactions.forEach((key, value)->{
            JSONObject reaction = new JSONObject();
            JSONObject summary = new JSONObject();
            summary.put("total_count", value);
            reaction.put("summary", summary);
            object.put(key, reaction);
        });

        return object;
    }
}
