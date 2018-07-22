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

public class TwitterFeedMapper implements FeedMapper {
    public List<FeedItem> getProcessedFeedItems(JSONObject jsonObject) {

        JSONArray array = jsonObject.getJSONArray("data");

        List<FeedItem> feedItemList = null;

        for (int i = 0; i < array.length(); i++) {

            JSONObject object = array.getJSONObject(i);

            FeedItem feedItem = new FeedItem();
            feedItem.identifier = object.getString("id_str");

            try {
                feedItem.publishedDate = new SimpleDateFormat("EE MMM dd hh:mm:ss Z yyyy", Locale.ENGLISH).parse(object.getString("created_at")) ;
            }catch (Exception e) {
                System.out.println(e);
            }

            if (object.getString("text") != null) {
                feedItem.contents.add(new Content(ContentType.TEXT, object.getString("text")));
            }

            feedItemList.add(feedItem);
        }

        return feedItemList;
    }
}
