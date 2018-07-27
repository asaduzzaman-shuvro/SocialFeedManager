package com.cefalo.school.mapper;

import com.cefalo.school.model.Content;
import com.cefalo.school.model.ContentType;
import com.cefalo.school.model.FeedItem;

import java.text.DateFormat;
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

            if(object.has("text")){
                feedItem.contents.add(new Content(ContentType.TEXT,
                        object.getString("text"), object.getString("text")));
            }

            if (object.has("user")){
                JSONObject user = object.getJSONObject("user");
                if(user.has("id_str")){
                    feedItem.userID = user.getString("id_str");
                }
            }

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
                            }else if(media.getString("type").equals("video")){
                                contentType = ContentType.VIDEO;
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

    @Override
    public JSONObject mapFeedItemToJSON(FeedItem item) {
        JSONObject object = new JSONObject();
        DateFormat df = new SimpleDateFormat("EE MMM dd hh:mm:ss Z yyyy");
        object.put("created_at", df.format(item.publishedDate));
        object.put("id_str", item.identifier);
        JSONObject user = new JSONObject();
        user.put("id_str", item.userID);
        object.put("user", user);

        String text = "";
        for (Content content:item.contents) {
            if(content.contentType == ContentType.TEXT && !content.description.isEmpty()){
                text = content.description;
                break;
            }
        }
        object.put("text", text);

        return object;
    }

}
