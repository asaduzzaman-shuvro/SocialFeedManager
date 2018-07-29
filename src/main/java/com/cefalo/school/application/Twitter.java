package com.cefalo.school.application;

import java.util.UUID;

public class Twitter implements Application {
    private String userName = "";
    private String authToken = "";
    private ApplicationType applicationType;
    private UUID applicationIdentifier;
    private String  userId = "";

    public Twitter(){
        this.applicationIdentifier = UUID.randomUUID();
        this.applicationType = ApplicationType.TWITTER;
        this.userId = "80177619";
        System.out.println("Twitter: " + applicationIdentifier);
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String getUserId() {
        return userId;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public UUID getApplicationIdentifier() {
        return applicationIdentifier;
    }

    public String getUserName() {
        return userName;
    }

    public String getAuthToken() {
        return authToken;
    }

    public ApplicationType getApplicationType() {
        return applicationType;
    }
}
