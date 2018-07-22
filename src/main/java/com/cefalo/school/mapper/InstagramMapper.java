package com.cefalo.school.mapper;

import com.cefalo.school.model.Content;
import com.cefalo.school.model.ContentType;
import com.cefalo.school.model.FeedItem;
import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class InstagramMapper implements FeedMapper {
    public List<FeedItem> getProcessedFeedItems() {
        JSONObject jsonObject = null;
        JSONArray array = jsonObject.getJSONArray("data");

        List<FeedItem> feedItemList = null;

        for (int i = 0; i < array.length(); i++) {

            JSONObject object = array.getJSONObject(i);

            FeedItem feedItem = new FeedItem();
            feedItem.identifier = object.getString("id");
            feedItem.publishedDate = new Date(object.getLong("created_time") * 1000L);

            if (object.getJSONObject("caption").getString("text") != nil) {
                feedItem.contents.add(new Content(ContentType.TEXT, object.getJSONObject("caption").getString("text")));
            }

            if (object.getString("type") == "image") {
                feedItem.contents.add(new Content(ContentType.URL, object.getJSONObject("images").getJSONObject("standard_resolution").getString("url")));
            }



            if (object.getString("type") == "video") {
                feedItem.contents.add(new Content(ContentType.URL, object.getJSONObject("videos").getJSONObject("standard_resolution").getString("url")));
            }
            

            feedItemList.add(feedItem);
        }

        return feedItemList;
    }
}
