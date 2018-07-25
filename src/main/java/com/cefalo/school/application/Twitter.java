package com.cefalo.school.application;

import java.util.UUID;

public class Twitter implements Application {
    private String userName;
    private String authToken;
    private ApplicationType applicationType;
    private UUID applicationIdentifier;

    public Twitter(){
        this.applicationIdentifier = UUID.randomUUID();
        this.applicationType = ApplicationType.TWITTER;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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
