package com.cefalo.school.mapper;

import com.cefalo.school.model.Content;
import com.cefalo.school.model.ContentType;
import com.cefalo.school.model.FeedItem;

import java.util.*;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;

public class TwitterFeedMapper implements FeedMapper {

    public List<FeedItem> getProcessedFeedItems(UUID applicationIdentifier, JSONObject jsonObject) {

        JSONArray array = jsonObject.getJSONArray("data");

        List<FeedItem> feedItemList = new ArrayList<FeedItem>();

        for (int i = 0; i < array.length(); i++) {

            JSONObject object = array.getJSONObject(i);

            FeedItem feedItem = new FeedItem();
            feedItem.applicationIdentifier = applicationIdentifier;
            feedItem.identifier = object.getString("id_str");

            try {
                feedItem.publishedDate = new SimpleDateFormat("EE MMM dd hh:mm:ss Z yyyy",
                        Locale.ENGLISH).parse(object.getString("created_at"));
            } catch (Exception e) {
                System.out.println(e);
            }

            feedItem.contents.add(new Content(ContentType.TEXT,
                    object.getString("text"), object.getString("text")));

            if (object.has("entities")) {
                JSONObject item = object.getJSONObject("entities");
                if (item.has("media")) {
                    JSONArray medias = item.getJSONArray("media");
                    for (Object mediaObj : medias) {
                        JSONObject media = (JSONObject) mediaObj;
                        ContentType contentType = ContentType.URL;
                        //TODO: determine video or photo
                        if (media.has("type")) {
                            if (media.getString("type").equals("photo")) {
                                contentType = ContentType.PICTURE;
                            }
                        }
                        feedItem.contents.add(new Content(contentType,
                                media.getString("media_url"), ""));

                    }
                }
            }
            feedItemList.add(feedItem);
        }
        return feedItemList;
    }

}
