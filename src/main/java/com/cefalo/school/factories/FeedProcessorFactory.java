package com.cefalo.school.factories;
import com.cefalo.school.application.Application;
import com.cefalo.school.processors.FeedProcessor;
import com.cefalo.school.processors.InstagramFeedProcessor;
import com.cefalo.school.application.ApplicationType;
import com.cefalo.school.processors.FacebookFeedProcessor;
import com.cefalo.school.processors.TwitterFeedProcessor;

public class FeedProcessorFactory {
    public static FeedProcessor getFeedProcessor(Application application){
        if (application.getApplicationType() == ApplicationType.FACEBOOK){
            return new FacebookFeedProcessor(application.getApplicationIdentifier());
        }
        else if (application.getApplicationType() == ApplicationType.TWITTER){
            return new TwitterFeedProcessor(application.getApplicationIdentifier());
        }
        else if (application.getApplicationType() == ApplicationType.INSTAGRAM){
            return new InstagramFeedProcessor(application.getApplicationIdentifier());
        }
        else
            return null;
    }
}
