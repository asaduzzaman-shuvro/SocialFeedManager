package com.cefalo.school.application;

import java.util.List;
import java.util.UUID;

public class AccountManager {
    private static AccountManager ourInstance = new AccountManager();
    private List<Application> supportedApplications;

    public static AccountManager getInstance() {
        return ourInstance;
    }

    private AccountManager() {
        Facebook fb = new Facebook();
        fb.setUserName("AMI");
        fb.setAuthToken("ASJAKJSHDKJASHDGFJHASJHDGFHAFSD");
        supportedApplications.add(fb);

        Twitter twitter= new Twitter();
        twitter.setUserName("AshifIqbal");
        twitter.setAuthToken("ASJAK896957658586587658765FHAFSD");
        supportedApplications.add(twitter);
    }

    public List<Application> getSupportedApplications() {
        return this.supportedApplications;
    }

    public String getAuthTokenByIdentifier(UUID appIdentifier){
        for (Application supportedApplication : supportedApplications) {
            if(supportedApplication.getApplicationIdentifier() == appIdentifier){
                return supportedApplication.getAuthToken();
            }
        }
        return null;
    }

    public void addSupportedApplication(Application application){

    }
}
