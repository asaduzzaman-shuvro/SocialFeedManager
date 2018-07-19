package com.cefalo.school.Factories;

import com.cefalo.school.Mapper.FacebookFeedMapper;
import com.cefalo.school.Mapper.TwitterFeedMapper;
import com.cefalo.school.application.ApplicationType;
import com.cefalo.school.services.FacebookFeedProcessor;
import com.cefalo.school.services.SocialFeedOperator;
import com.cefalo.school.services.TwitterFeedProcessor;

public class FeedProcessorFactory {
    public static SocialFeedOperator getFeedOperator(ApplicationType applicationType){
        if (applicationType == ApplicationType.FACEBOOK){
            return new SocialFeedOperator(new FacebookFeedMapper(), new FacebookFeedProcessor());
        }
        else if (applicationType == ApplicationType.TWITTER){
            return new SocialFeedOperator(new TwitterFeedMapper(), new TwitterFeedProcessor());
        }
        else
            return null;
    }
}
