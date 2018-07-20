package com.cefalo.school.factories;
import com.cefalo.school.processors.FeedProcessor;
import com.cefalo.school.processors.InstagramFeedProcessor;
import com.cefalo.school.application.ApplicationType;
import com.cefalo.school.processors.FacebookFeedProcessor;
import com.cefalo.school.processors.TwitterFeedProcessor;

public class FeedProcessorFactory {
    public static FeedProcessor getFeedOperator(ApplicationType applicationType){
        if (applicationType == ApplicationType.FACEBOOK){
            return new FacebookFeedProcessor();
        }
        else if (applicationType == ApplicationType.TWITTER){
            return new TwitterFeedProcessor();
        }
        else if (applicationType == ApplicationType.INSTAGRAM){
            return new InstagramFeedProcessor();
        }
        else
            return null;
    }
}
