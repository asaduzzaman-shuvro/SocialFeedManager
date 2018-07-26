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
    public List<FeedItem> getProcessedFeedItems(UUID applicationIdentifier, JSONObject jsonObject) {

        JSONArray data = jsonObject.getJSONArray("data");

        List<FeedItem> feedItemList = new ArrayList<FeedItem>();

        Map<String, ContentType> keyMap = new HashMap<String, ContentType>(){{
            put("status", ContentType.TEXT);
            put("link", ContentType.URL);
            put("video", ContentType.VIDEO);
            put("photo", ContentType.PICTURE);
        }};

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
                feedItem.publishedDate = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ssZ", Locale.ENGLISH).parse(object.getString("created_time")) ;
                feedItem.lastModifiedDate = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ssZ", Locale.ENGLISH).parse(object.getString("updated_time")) ;
            }catch (Exception e) {
                e.printStackTrace();
            }

            for (String itemType : keyMap.keySet()) {
                if(object.has("type") && object.getString("type").equals(itemType)){
                    feedItem.contents.add(new Content(keyMap.get(itemType), object.has("message")?object.getString("message") : "", ""));
                }
            }

            for (String reaction : reactionKeyList) {
                if(object.has(reaction)){
                    feedItem.reactions.put(reaction, object.getJSONObject(reaction).getJSONObject("summary").getInt("total_count"));
                }
            }

            if(object.has("comments")){
                JSONArray comments = object.getJSONObject("comments").getJSONArray("data");
                for (Object commentObj : comments) {
                    JSONObject comment = (JSONObject) commentObj;
                    FeedItem commentFeed = new FeedItem();
                    commentFeed.identifier = comment.getString("id");
                    try {
                        commentFeed.publishedDate = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ssZ", Locale.ENGLISH).parse(comment.getString("created_time")) ;
                        commentFeed.lastModifiedDate = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ssZ", Locale.ENGLISH).parse(comment.getString("updated_time")) ;
                    }catch (Exception e) {
                        e.printStackTrace();
                    }

                    for (String itemType : keyMap.keySet()) {
                        if(comment.has("type") && comment.getString("type").equals(itemType)){
                            commentFeed.contents.add(new Content(keyMap.get(itemType), comment.has("message")?comment.getString("message") : "", ""));
                        }
                    }

                    feedItem.comments.add(commentFeed);
                }
            }


            feedItemList.add(feedItem);
        }

        return feedItemList;
    }

    @Override
    public JSONObject mapFeedItemToJSON(FeedItem item) {
        return null;
    }
}
