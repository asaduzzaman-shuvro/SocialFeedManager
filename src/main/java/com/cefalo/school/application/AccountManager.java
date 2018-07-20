package com.cefalo.school.application;

import java.util.List;

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
        fb.setApplicationType(ApplicationType.FACEBOOK);
        supportedApplications.add(fb);

        Twitter twitter= new Twitter();
        twitter.setUserName("AshifIqbal");
        twitter.setAuthToken("ASJAK896957658586587658765FHAFSD");
        twitter.setApplicationType(ApplicationType.TWITTER);
        supportedApplications.add(twitter);
    }

    public List<Application> getSupportedApplications() {
        return this.supportedApplications;
    }

    public void addSupportedApplication(Application application){

    }
}
