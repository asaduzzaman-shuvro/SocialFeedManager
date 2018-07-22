package com.cefalo.school.mapper;

import com.cefalo.school.model.Content;
import com.cefalo.school.model.ContentType;
import com.cefalo.school.model.FeedItem;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
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

        Map<String, ContentType> keyMap = new HashMap<String, ContentType>(){{
            put("message", ContentType.TEXT);
            put("links", ContentType.URL);
            put("video", ContentType.VIDEO);
            put("image", ContentType.PICTURE);
        }};

        for (Object item : data) {

            JSONObject object = (JSONObject) item;

            FeedItem feedItem = new FeedItem();
            feedItem.identifier = object.getString("id");
            try {
                feedItem.publishedDate = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ssZ", Locale.ENGLISH).parse(object.getString("created_time")) ;
            }catch (Exception e) {
                e.printStackTrace();
            }

            for (String itemType : keyMap.keySet()) {
                if(object.has(itemType)){
                    feedItem.contents.add(new Content(keyMap.get(itemType), object.getString(itemType)));
                }
            }

            feedItemList.add(feedItem);
        }

        return feedItemList;
    }
}
