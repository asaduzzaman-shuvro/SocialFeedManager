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

            if (object.has("caption")) {
                feedItem.contents.add(new Content(ContentType.TEXT, object.getJSONObject("caption").getString("text")));
            }

            if (object.has("type") && object.getString("type") == "image") {
                feedItem.contents.add(new Content(ContentType.URL, object.getJSONObject("images").getJSONObject("standard_resolution").getString("url")));
            }

            if (object.has("type") && object.getString("type") == "video") {
                feedItem.contents.add(new Content(ContentType.URL, object.getJSONObject("videos").getJSONObject("standard_resolution").getString("url")));
            }

            feedItemList.add(feedItem);
        }

        return feedItemList;
    }
}
