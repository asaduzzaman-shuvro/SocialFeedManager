package com.cefalo.school.mapper;

import com.cefalo.school.model.Content;
import com.cefalo.school.model.ContentType;
import com.cefalo.school.model.FeedItem;

import java.util.ArrayList;
import java.util.UUID;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Date;
import java.util.List;

public class InstagramFeedMapper implements FeedMapper {

    public List<FeedItem> getProcessedFeedItems(UUID applicationIdentifier, JSONObject jsonObject) {

        JSONArray data = jsonObject.getJSONArray("data");

        List<FeedItem> feedItemList = new ArrayList<FeedItem>();

        for (Object item : data) {

            JSONObject object = (JSONObject) item;

            FeedItem feedItem = new FeedItem();
            feedItem.identifier = object.getString("id");
            feedItem.publishedDate = new Date(object.getLong("created_time") * 1000L);
            feedItem.applicationIdentifier = applicationIdentifier;

            if (object.has("user")){
                JSONObject user = object.getJSONObject("user");
                if (user.has("id")){
                    feedItem.userID = user.getString("id");
                }
            }

            if (object.has("caption")) {
                feedItem.contents.add(new Content(ContentType.TEXT, object.getJSONObject("caption").getString("text"), ""));
            }

            if (object.has("type") && object.getString("type") == "image") {
                feedItem.contents.add(new Content(ContentType.URL, object.getJSONObject("images").getJSONObject("standard_resolution").getString("url"), ""));
            }

            if (object.has("type") && object.getString("type") == "video") {
                feedItem.contents.add(new Content(ContentType.URL, object.getJSONObject("videos").getJSONObject("standard_resolution").getString("url"), ""));
            }

            feedItemList.add(feedItem);
        }

        return feedItemList;
    }

    @Override
    public JSONObject mapFeedItemToJSON(FeedItem item) {

        JSONObject object = new JSONObject();
        object.put("id",item.identifier);
        object.put("created_time",new Date().getTime());

        String text = "";
        for (Content content:item.contents) {
            if(content.contentType == ContentType.TEXT && !content.description.isEmpty()){
                text = content.description;
                break;
            }
        }

        JSONObject captionObject = new JSONObject();
        captionObject.put("text",text);
        object.put("caption",captionObject);

        JSONObject user = new JSONObject();
        user.put("id",item.userID);
        object.put("user",user);

        return object;
    }
}
