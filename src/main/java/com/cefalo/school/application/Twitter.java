package com.cefalo.school.application;

import java.util.UUID;

public class Twitter implements Application {
    private String userName;
    private String authToken;
    private ApplicationType applicationType;

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public void setApplicationType(ApplicationType applicationType) {
        this.applicationType = applicationType;
    }

    public void setApplicationIdentifier(UUID identifier) {

    }

    public UUID getApplicationIdentifier() {
        return null;
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
