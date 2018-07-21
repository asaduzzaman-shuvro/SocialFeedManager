package com.cefalo.school.operators;

import com.cefalo.school.model.FeedItem;
import com.thirdparty.api.FacebookApi;
import org.json.JSONObject;

public class FacebookOperator implements FeedOperator {
    public  JSONObject jsonObject = null;

    public boolean postUpdate(FeedItem item) {

        return false;
    }

    public boolean getFeed() {
        jsonObject = FacebookApi.getFeeds();
        return jsonObject != null;
    }

}
