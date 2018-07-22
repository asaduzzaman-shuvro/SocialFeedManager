package com.cefalo.school.mapper;

import com.cefalo.school.model.Content;
import com.cefalo.school.model.ContentType;
import com.cefalo.school.model.FeedItem;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class FacebookFeedMapper implements FeedMapper {
    public List<FeedItem> getProcessedFeedItems(JSONObject jsonObject) {

        JSONArray data = jsonObject.getJSONArray("data");

        List<FeedItem> feedItemList = new ArrayList<FeedItem>();

        for (Object item : data) {

            JSONObject object = (JSONObject) item;

            FeedItem feedItem = new FeedItem();
            feedItem.identifier = object.getString("id");
            try {
                feedItem.publishedDate = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ssZ", Locale.ENGLISH).parse(object.getString("created_time")) ;
            }catch (Exception e) {
                e.printStackTrace();
            }

            if (object.has("message")) {
                feedItem.contents.add(new Content(ContentType.TEXT, object.getString("message")));
            }

            if (object.has("links")) {
                feedItem.contents.add(new Content(ContentType.URL, object.getString("links")));
            }

            if (object.has("video")) {
                feedItem.contents.add(new Content(ContentType.VIDEO, object.getString("video")));
            }

            if (object.has("image")) {
                feedItem.contents.add(new Content(ContentType.PICTURE, object.getString("image")));
            }

            feedItemList.add(feedItem);
        }

        return feedItemList;
    }
}
