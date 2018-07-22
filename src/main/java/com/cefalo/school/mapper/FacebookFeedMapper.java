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

public class FacebookFeedMapper implements FeedMapper {
    public List<FeedItem> getProcessedFeedItems() {

        JSONObject jsonObject = null;
        JSONArray array = jsonObject.getJSONArray("data");

        List<FeedItem> feedItemList = null;

        for (int i = 0; i < array.length(); i++) {

            JSONObject object = array.getJSONObject(i);

            FeedItem feedItem = new FeedItem();
            feedItem.identifier = object.getString("id");
            try {
                feedItem.publishedDate = new SimpleDateFormat("yyyy-MM-ddThh:mm:ssZ", Locale.ENGLISH).parse(object.getString("created_time")) ;
            }catch (Exception e) {
                System.out.println(e);
            }

            if (object.getString("message") != null) {
                feedItem.contents.add(new Content(ContentType.TEXT, object.getString("message")));
            }

            if (object.getString("links") != null) {
                feedItem.contents.add(new Content(ContentType.URL, object.getString("links")));
            }

            if (object.getString("video") != null) {
                feedItem.contents.add(new Content(ContentType.VIDEO, object.getString("video")));
            }

            if (object.getString("image") != null) {
                feedItem.contents.add(new Content(ContentType.PICTURE, object.getString("image")));
            }

            feedItemList.add(feedItem);
        }

        return feedItemList;
    }
}
