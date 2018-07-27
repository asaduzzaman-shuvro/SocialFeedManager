package com.cefalo.school.mapper;

import com.cefalo.school.model.Content;
import com.cefalo.school.model.ContentType;
import com.cefalo.school.model.FeedItem;
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
        put("link", ContentType.URL);
        put("video", ContentType.VIDEO);
        put("photo", ContentType.PICTURE);
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

            FeedItem feedItem = new FeedItem();
            feedItem.identifier = object.getString("id");
            feedItem.applicationIdentifier = applicationIdentifier;
            try {
                feedItem.publishedDate = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ssZ", Locale.ENGLISH)
                    .parse(object.getString("created_time")) ;
                feedItem.lastModifiedDate = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ssZ", Locale.ENGLISH)
                    .parse(object.getString("updated_time")) ;
            }catch (Exception e) {
                e.printStackTrace();
            }

            if(object.has("type")) {
                String feedType = object.getString("type");
                if (feedType.equals("status")){
                    feedItem.contents.add(new Content(keyMap.get(feedType), "",
                        object.has("message") ? object.getString("message") : ""));
                }else {
                    JSONArray attachments = object.getJSONObject("attachments").getJSONArray("data");
                    mapAttachments(feedItem, attachments, object.getString("type"));
                    if(feedItem.contents.size() > 0 && "".equals(feedItem.contents.get(0).description) && object.has("message")){
                        feedItem.contents.get(0).description = object.getString("message");
                    }
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
                item.contents.add(new Content(ContentType.PICTURE, object.getString("url"),
                    object.has("description")?object.getString("description"):""));
            }else{
                item.contents.add(new Content(keyMap.get(itemType),object.getString("url"),
                    object.has("description")?object.getString("description"):""));
            }
        }
        return null;
    }

    @Override
    public JSONObject mapFeedItemToJSON(FeedItem item) {
        return null;
    }
}
