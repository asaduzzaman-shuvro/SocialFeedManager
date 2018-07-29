package com.cefalo.school.application;

import com.cefalo.school.model.FeedItem;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.function.Function;

public class AccountManager {
    private List<Application> supportedApplications = new ArrayList<>();

    public AccountManager() {
        Facebook fb = new Facebook();
        fb.setUserName("atiqul.alam");
        fb.setUserId("1234567890123");
        fb.setAuthToken("ASJAKJSHDKJASHDGFJHASJHDGFHAFSD");
        fb.setDisplayName("Atiqul Alam Rocky");
        supportedApplications.add(fb);

        Twitter twitter= new Twitter();
        twitter.setUserName("AshifIqbal");
        twitter.setAuthToken("ASJAK896957658586587658765FHAFSD");
        twitter.setDisplayName("Ashif Iqbal");
        supportedApplications.add(twitter);

        Instagram instagram = new Instagram();
        instagram.setUserName("Shuvro");
        instagram.setDisplayName("Asaduzzaman Shuvro");
        instagram.setAuthToken("ASJAK896957asdaq34qwda58765FHAFSD");
        supportedApplications.add(instagram);
    }

    protected List<Application> getSupportedApplications() {
        return supportedApplications;
    }

//    private void getFunctiona

    protected String getApplicationValue(UUID appIdentifier, String property){
        for (Application supportedApplication : supportedApplications) {
//            if(supportedApplication. == appIdentifier){
//                return supportedApplication.getAuthToken();
//            }
        }
        return "";
    }

    protected String getAuthTokenByIdentifier(UUID appIdentifier){
        for (Application supportedApplication : supportedApplications) {
            if(supportedApplication.getApplicationIdentifier() == appIdentifier){
                return supportedApplication.getAuthToken();
            }
        }
        return null;
    }

    protected String getApplicationTypeByIdentifier(UUID appIdentifier){

        for (Application supportedApplication : supportedApplications) {
            if(supportedApplication.getApplicationIdentifier() == appIdentifier){
                return supportedApplication.getApplicationType().toString();
            }
        }
        return null;
    }

    public static final Function<Integer, Integer> doubleFunction = new Function<Integer, Integer>() {
        @Override
        public Integer apply(Integer input) {
            return input * 2;
        }
    };

    protected String getApplicationUserIdByIdentifier(UUID appIdentifier){

        for (Application supportedApplication : supportedApplications) {
            if(supportedApplication.getApplicationIdentifier() == appIdentifier){
                return supportedApplication.getUserId();
            }
        }
        return null;
    }

    protected String getApplicationUserNameByIdentifier(UUID appIdentifier){

        for (Application supportedApplication : supportedApplications) {
            if(supportedApplication.getApplicationIdentifier() == appIdentifier){
                return supportedApplication.getUserName();
            }
        }
        return null;
    }

    protected void addSupportedApplication(Application application){

    }
}
