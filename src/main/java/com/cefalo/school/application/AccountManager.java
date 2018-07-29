package com.cefalo.school.application;

import com.cefalo.school.model.FeedItem;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AccountManager {
    private List<Application> supportedApplications = new ArrayList<>();

    public AccountManager() {
        Facebook fb = new Facebook();
        fb.setUserName("atiqul.alam");
        fb.setUserId("1234567890123");
        fb.setUserDisplayName("Atiqul Alam");
        fb.setAuthToken("ASJAKJSHDKJASHDGFJHASJHDGFHAFSD");
        supportedApplications.add(fb);

        Twitter twitter= new Twitter();
        twitter.setUserName("AshifIqbal");
        twitter.setUserDisplayName("AshifIqbal");
        twitter.setAuthToken("ASJAK896957658586587658765FHAFSD");
        supportedApplications.add(twitter);

        Instagram instagram = new Instagram();
        instagram.setUserName("Shuvro");
        instagram.setUserDisplayName("Asaduzzaman Shuvro");
        instagram.setAuthToken("ASJAK896957asdaq34qwda58765FHAFSD");
        supportedApplications.add(instagram);
    }

    protected List<Application> getSupportedApplications() {
        return supportedApplications;
    }

    protected String getAuthTokenByIdentifier(UUID appIdentifier){
        Application application = supportedApplications.stream().filter(app->{
            return app.getApplicationIdentifier() == appIdentifier;
        }).findAny().orElse(null);
        if(application != null){
            return application.getAuthToken();
        }
        return "";
    }

    protected String getApplicationUserIdByIdentifier(UUID appIdentifier){

        Application application = supportedApplications.stream().filter(app->{
            return app.getApplicationIdentifier() == appIdentifier;
        }).findAny().orElse(null);
        if(application != null){
            return application.getUserId();
        }
        return "";
    }


    protected String getApplicationUserDisplayNameByIdentifier(UUID appIdentifier){
        Application application = supportedApplications.stream().filter(app->{
            return app.getApplicationIdentifier() == appIdentifier;
        }).findAny().orElse(null);
        if(application != null){
            return application.getUserDisplayName();
        }
        return "";
    }

    protected String getApplicationNameByIdentifier(UUID appIdentifier){
        Application application = supportedApplications.stream().filter(app->{
           return app.getApplicationIdentifier() == appIdentifier;
        }).findAny().orElse(null);
        if(application != null){
            return application.getApplicationType().toString();
        }
        return "";
    }

    protected void addSupportedApplication(Application application){

    }
}
