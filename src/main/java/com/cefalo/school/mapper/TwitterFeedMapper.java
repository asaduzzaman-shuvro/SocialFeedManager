package com.cefalo.school.mapper;

import com.cefalo.school.model.*;

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

            TwitterFeedItem feedItem = new TwitterFeedItem();
            feedItem.applicationIdentifier = applicationIdentifier;
            feedItem.identifier = object.getString("id_str");

            try {
                feedItem.publishedDate = new SimpleDateFormat("EE MMM dd hh:mm:ss Z yyyy",
                        Locale.ENGLISH).parse(object.getString("created_at"));
            } catch (Exception e) {
                System.out.println(e);
            }

            if(object.has("text")){
                if (!object.getString("text").isEmpty()){
                    feedItem.contents.add(new Content(ContentType.TEXT,
                            "", object.getString("text")));
                }
            }

            if(object.has("retweet_count")){
                feedItem.retweetCount = object.getInt("retweet_count");
            }
            if(object.has("favorite_count")){
                feedItem.favoriteCount= object.getInt("favorite_count");
            }
            if (object.has("user")){
                JSONObject user = object.getJSONObject("user");
                if(user.has("id_str")){
                    feedItem.userID = user.getString("id_str");
                }
                if(user.has("screen_name")){
                    feedItem.displayName = user.getString("screen_name");
                }else if(user.has("name")){
                    feedItem.displayName = user.getString("name");
                }
            }

            if (object.has("entities")) {
                JSONObject item = object.getJSONObject("entities");
                if (item.has("media")) {
                    JSONArray medias = item.getJSONArray("media");
                    for (Object mediaObj : medias) {
                        JSONObject media = (JSONObject) mediaObj;
                        ContentType contentType = ContentType.PICTURE;
                        if (media.has("type")) {
                            if(media.getString("type").equals("video")){
                                contentType = ContentType.VIDEO;
                            }
                        }
                        feedItem.contents.add(new Content(contentType,
                                media.getString("media_url"), ""));
                    }
                }
                if (item.has("comments")){
                    JSONArray comments = item.getJSONArray("comments");
                    for (Object commentObj : comments) {
                        JSONObject comment = (JSONObject) commentObj;
                        if (comment.has("text")){
                            String text = comment.getString("text");
                            String commenter = "";
                            Date commentDate = new Date();
                            try {
                                commentDate = new SimpleDateFormat("EE MMM dd hh:mm:ss Z yyyy",
                                        Locale.ENGLISH).parse(object.getString("created_at"));
                            } catch (Exception e) {
                                System.out.println(e);
                            }
                            if(comment.has("commenter_name")){
                                commenter = comment.getString("commenter_name");
                            }

                            feedItem.comments.
                                    add(new Comment(comment.getString("id_str"),
                                            comment.getString("text"),
                                            commentDate, commenter, ));
                        }

                    }
                }
            }
            feedItemList.add(feedItem);
        }
        return feedItemList;
    }

    @Override
    public JSONObject mapFeedItemToJSON(FeedItem item) {
        TwitterFeedItem tweetItem = (TwitterFeedItem)item;
        JSONObject object = new JSONObject();
        DateFormat df = new SimpleDateFormat("EE MMM dd hh:mm:ss Z yyyy");
        object.put("created_at", df.format(item.publishedDate));
        object.put("id_str", item.identifier);
        JSONObject user = new JSONObject();
        user.put("id_str", item.userID);
        user.put("screen_name", item.displayName);
        object.put("user", user);

        object.put("retweet_count", tweetItem.retweetCount);
        object.put("favorite_count", tweetItem.favoriteCount);

        JSONObject entities = new JSONObject();
        JSONArray media = new JSONArray();

        entities.put("media", media);
        object.put("entities", entities);

        String text = "";
        for (Content content:item.contents) {
            if(content.contentType == ContentType.TEXT && !content.description.isEmpty()){
                text = content.description;
            }
            else if(content.contentType == ContentType.PICTURE && !content.value.isEmpty()){
                if (object.has("entities")) {
                    JSONObject entitieObject = object.getJSONObject("entities");
                    if (entitieObject.has("media")) {
                        JSONArray medias = entitieObject.getJSONArray("media");
                        JSONObject mediaObject = new JSONObject();
                        mediaObject.put("type", "photo");
                        mediaObject.put("media_url", content.value);
                        medias.put(mediaObject);
                    }
                }
            }
            else if(content.contentType == ContentType.VIDEO && !content.value.isEmpty()){
                if (object.has("entities")) {
                    JSONObject entitieObject = object.getJSONObject("entities");
                    if (entitieObject.has("media")) {
                        JSONArray medias = entitieObject.getJSONArray("media");
                        JSONObject mediaObject = new JSONObject();
                        mediaObject.put("type", "video");
                        mediaObject.put("media_url", content.value);
                        medias.put(mediaObject);
                    }
                }
            }
        }
        object.put("text", text);

        JSONArray comments = new JSONArray();
        for(Comment comment:tweetItem.comments){
            JSONObject commentItem = new JSONObject();
            commentItem.put("text", comment.text);
            commentItem.put("id_str", comment.identifier);
            commentItem.put("created_at", df.format(comment.publishDate));
            commentItem.put("commenter_name", comment.commenteDisplayName);
            comments.put(commentItem);
        }

        entities.put("comments", comments);
        return object;
    }

}
