package com.cefalo.school.mapper;

import com.cefalo.school.model.Content;
import com.cefalo.school.model.ContentType;
import com.cefalo.school.model.FeedItem;

import java.util.ArrayList;
import java.util.UUID;

import netscape.javascript.JSObject;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Date;
import java.util.List;

public class InstagramFeedMapper implements FeedMapper {

    public List<FeedItem> getProcessedFeedItems(UUID applicationIdentifier, JSONObject jsonObject) {

        List<FeedItem> feedItemList = new ArrayList<FeedItem>();

        if (jsonObject.has("graphql")){
            JSONObject graphphql = jsonObject.getJSONObject("graphql");
            if (graphphql.has("user")){
                JSONObject userData = graphphql.getJSONObject("user");
                if (userData.has("edge_owner_to_timeline_media")){
                    JSONObject timelineData = userData.getJSONObject("edge_owner_to_timeline_media");
                    if (timelineData.has("edges")){
                        JSONArray allNodes = timelineData.getJSONArray("edges");
                        for (Object node : allNodes) {
                            JSONObject object = ((JSONObject) node).getJSONObject("node");

                            FeedItem feedItem = new FeedItem();
                            if (object.has("id")){
                                feedItem.identifier = object.getString("id");
                            }
                            if (object.has("taken_at_timestamp")){
                                feedItem.publishedDate = new Date(object.getLong("taken_at_timestamp") * 1000L);
                            }
                            feedItem.applicationIdentifier = applicationIdentifier;

                            if (object.has("edge_media_to_caption")){
                                JSONObject textMediaObject = object.getJSONObject("edge_media_to_caption");
                                if (textMediaObject.has("edges")){
                                    JSONArray textEgges = textMediaObject.getJSONArray("edges");
                                    for (Object textEdge : textEgges  ) {
                                        JSONObject textObject = (JSONObject) textEdge;
                                        feedItem.contents.add(new Content(ContentType.TEXT, textObject.getJSONObject("node").getString("text"), ""));

                                    }
                                }
                            }
                            if (object.has("is_video") && !object.getBoolean("is_video")){
                                if (object.has("thumbnail_src")){
                                    feedItem.contents.add(new Content(ContentType.PICTURE, object.getString("thumbnail_src"), ""));
                                }
                            } else if (object.has("is_video") && object.getBoolean("is_video")){
                                if (object.has("thumbnail_src")){
                                    feedItem.contents.add(new Content(ContentType.VIDEO, object.getString("thumbnail_src"), ""));
                                }
                            }

                            if (object.has("owner")){
                                JSONObject userInfo = object.getJSONObject("owner");
                                if (userInfo.has("id")){
                                    feedItem.userID = userInfo.getString("id");
                                }
                            }

                            feedItemList.add(feedItem);
                        }
                    }
                }
            }
        }
        for (FeedItem feed : feedItemList) {
            System.out.println(feed.publishedDate);
        }

        return feedItemList;
    }

    @Override
    public JSONObject mapFeedItemToJSON(FeedItem item) {

        JSONObject object = new JSONObject();

        JSONObject  nodeObject = new JSONObject();
        nodeObject.put("id",item.identifier.toString());
        nodeObject.put("taken_at_timestamp",new Date().getTime());


        JSONObject edge_media_to_caption = new JSONObject();
        JSONArray captionEges = new JSONArray();


        for (Content content:item.contents) {
            if(content.contentType == ContentType.TEXT && !content.description.isEmpty()){
                JSONObject node = new JSONObject();
                node.put("text",content.value);
                captionEges.put(new JSONObject().put("node",node));
            }
        }

        edge_media_to_caption.put("edges",captionEges);
        nodeObject.put("edge_media_to_caption",edge_media_to_caption);

        object.put("node",nodeObject);

//        System.out.println(object);
        return object;
    }
}
